package shop.uz.onlineshopbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import shop.uz.onlineshopbot.bot.MainBot;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.enums.UserState;
import shop.uz.onlineshopbot.service.CategoryService;
import shop.uz.onlineshopbot.service.UserService;
import shop.uz.onlineshopbot.utils.MessageUtils;
import shop.uz.onlineshopbot.utils.ReplyKeyboardButton;

import static shop.uz.onlineshopbot.enums.UserState.*;

@Component
@Slf4j
public class UpdateController {
    String tx = "";
    private MainBot mainBot;
    private final MessageUtils messageUtils;

    private final UserService userService;

    private final CategoryService categoryService;
    private final ReplyKeyboardButton replyKeyboardButton;
    private User currentUser;

    public UpdateController(MessageUtils messageUtils, UserService userService,
                            CategoryService categoryService, ReplyKeyboardButton replyKeyboardButton) {
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
        if (message.hasText()) {
            processTextMessage(update);
        } else {
            processNotFound(message);
        }
    }

    private void processTextMessage(Update update) {
        String text = update.getMessage().getText();
        switch (text) {
            case "/start": {
                var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!", "Mahsulotlar \uD83C\uDFEC");
                senderMessage(sendMessage, START);
                break;
            }
            case "Mahsulotlar \uD83C\uDFEC": {
                var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang", categoryService.findAll());
                senderMessage(sendMessage, MENU);
                break;
            }
            default: {
                if (currentUser.getState().equals(MENU)) {
                    categoryService.findAll().forEach(c -> {
                        if (text.equals(c.getName())) {
                            tx = c.getName();
                        }
                    });
                    if (text.equals(tx)) {
                        Category category = categoryService.findAllByName(tx);
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!", categoryService.findAllByParentId(category.getId()));
                        senderMessage(sendMessage, INLINE);
                    } else if (text.equals("Orqaga ⬅\uFE0F")) {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!", "Mahsulotlar \uD83C\uDFEC");
                        senderMessage(sendMessage, START);
                    } else {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang", categoryService.findAll());
                        senderMessage(sendMessage, MENU);
                    }
                } else if (currentUser.getState().equals(INLINE)) {
                    if (text.equals("Orqaga ⬅\uFE0F")) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang", categoryService.findAll());
                        senderMessage(sendMessage, MENU);
                    } else {
                        Category category = categoryService.findAllByName(tx);
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!", categoryService.findAllByParentId(category.getId()));
                        senderMessage(sendMessage, INLINE);
                    }
                } else if (currentUser.getState().equals(START)) {
                    var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!", "Mahsulotlar \uD83C\uDFEC");
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

    private void senderMessage(SendMessage sendMessage, UserState state) {
        setView(sendMessage);
        currentUser.setState(state);
        userService.update(currentUser.getId(), currentUser);
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
