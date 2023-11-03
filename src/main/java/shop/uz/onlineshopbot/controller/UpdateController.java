package shop.uz.onlineshopbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import shop.uz.onlineshopbot.bot.MainBot;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.service.CategoryService;
import shop.uz.onlineshopbot.service.UserService;
import shop.uz.onlineshopbot.utils.MessageUtils;
import shop.uz.onlineshopbot.utils.ReplyKeyboardButton;

import static shop.uz.onlineshopbot.enums.UserState.*;

@Component
@Slf4j
public class UpdateController {
    private MainBot mainBot;
    private final MessageUtils messageUtils;

    private final UserService userService;

    private final CategoryService categoryService;
    private final ReplyKeyboardButton replyKeyboardButton;
    private User currentUser;

    public UpdateController(MessageUtils messageUtils, UserService userService, CategoryService categoryService, ReplyKeyboardButton replyKeyboardButton) {
        this.messageUtils = messageUtils;
        this.userService = userService;
        this.categoryService = categoryService;
        this.replyKeyboardButton = replyKeyboardButton;
    }

    public void registerBot(MainBot mainBot) {
        this.mainBot = mainBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            return;
        }
        if (update.getMessage() != null) {
            distrubuteMessageType(update);
        }
    }

    private void distrubuteMessageType(Update update) {
        var message = update.getMessage();
        currentUser = findCurrentUser(update);
        log.info(currentUser.getState().toString());
        if (message.hasText()) {
            processTextMessage(update);
        }else {
            processNotFound(message);
        }
    }

    private void processTextMessage(Update update) {
        String text = update.getMessage().getText();
        switch (text) {
            case "/start" : {
                var sendMessage = replyKeyboardButton.firstKeyboard(update,"Quyidagilardan birini tanlang!","Mahsulotlar \uD83C\uDFEC");
                setView(sendMessage);
                break;
            }
            case "Mahsulotlar \uD83C\uDFEC" : {
                var sendMessage = replyKeyboardButton.secondKeyboard(update,"Ro'yxatdan birini tanlang",categoryService.findAll());
                setView(sendMessage);
                currentUser.setState(MENU);
                userService.update(currentUser.getId(),currentUser);
                break;
            }
            default: {
                if (currentUser.getState().equals(MENU)) {
                    if (text.equals("Yangi Mahsulotlar \uD83C\uDFEC")) {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update,"Oziq ovqat mahsulotlar!","Oziq ovqat mahsulotlar \uD83C\uDFEC");
                        setView(sendMessage);
                        currentUser.setState(USER_PHONE_NUMBER);
                        userService.update(currentUser.getId(),currentUser);
                    }else {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update,"Yangi mahsulotlar!","Yangi Mahsulotlar \uD83C\uDFEC");
                        setView(sendMessage);
                        currentUser.setState(MENU);
                        userService.update(currentUser.getId(),currentUser);
                    }
                }else if (currentUser.getState().equals(USER_PHONE_NUMBER)) {
                    currentUser.setPhoneNumber(text);
                    var sendMessage = messageUtils.generateSendMessageWithText(update,"Siz ro'yxatdan o'tdingiz!");
                    setView(sendMessage);
                    currentUser.setState(REGISTER);
                    userService.update(currentUser.getId(),currentUser);
                }else if (currentUser.getState().equals(REGISTER)) {
                    var sendMessage = messageUtils.generateSendMessageWithText(update,"Iltimos admin javobini kuting!");
                    setView(sendMessage);
                    currentUser.setState(REGISTER);
                    userService.update(currentUser.getId(),currentUser);
                }else if (currentUser.getState().equals(START)) {
                    var sendMessage = replyKeyboardButton.firstKeyboard(update,"Quyidagilardan birini tanlang!","Mahsulotlar \uD83C\uDFEC");
                    setView(sendMessage);
                }
                break;
            }
        }
        log.info(update.getMessage().getText());

    }


    private void processNotFound(Message message) {

    }

    private void setView(SendMessage sendMessage) {
        mainBot.sendAnswerMessage(sendMessage);
    }

    private User findCurrentUser(Update update) {
        for (User currentUser : userService.findAll()) {
            if (update.getMessage().getChatId().equals(currentUser.getChatId())) {
                return currentUser;
            }
        }
        User user1 = new User();
        user1.setChatId(update.getMessage().getChatId());
        userService.create(user1);
        return user1;
    }
}
