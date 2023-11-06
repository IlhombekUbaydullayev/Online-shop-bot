package shop.uz.onlineshopbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import shop.uz.onlineshopbot.bot.MainBot;
import shop.uz.onlineshopbot.entities.Address;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.enums.UserState;
import shop.uz.onlineshopbot.service.AddressService;
import shop.uz.onlineshopbot.service.CategoryService;
import shop.uz.onlineshopbot.service.UserService;
import shop.uz.onlineshopbot.utils.MessageUtils;
import shop.uz.onlineshopbot.utils.ReplyKeyboardButton;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import static shop.uz.onlineshopbot.enums.UserState.*;
import static shop.uz.onlineshopbot.utils.Emojies.*;
import static shop.uz.onlineshopbot.utils.TranslationCode.*;

@Component
@Slf4j
public class UpdateController {
    String tx = "";
    private MainBot mainBot;
    private final MessageUtils messageUtils;

    private final UserService userService;

    private final CategoryService categoryService;

    private final AddressService addressService;

    private final ReplyKeyboardButton replyKeyboardButton;
    ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
    Locale locale = null;
    int isChecked;
    private User currentUser;

    public UpdateController(MessageUtils messageUtils, UserService userService,
                            CategoryService categoryService, AddressService addressService, ReplyKeyboardButton replyKeyboardButton) {
        this.messageUtils = messageUtils;
        this.userService = userService;
        this.categoryService = categoryService;
        this.addressService = addressService;
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
        } else if (message.hasLocation()) {
            processLocation(update);
        } else {
            processNotFound(message);
        }
    }

    private void processTextMessage(Update update) {
        String text = update.getMessage().getText();
        bundle.setBasenames("text");
        bundle.setDefaultEncoding("UTF-8");
        locale = new Locale("uz");
        switch (text) {
            case "/start": {
                var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!",
                        senderButtonMessage(BTN_PRODUCtS), senderButtonMessage(BTN_ORDERS), BTN_COMMENT_EMOJI +
                                senderButtonMessage(BTN_COMMENT),BTN_SETTING_EMOJI+senderButtonMessage(BTN_SETTING));
                senderMessage(sendMessage, START);
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
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getId()));
                        senderMessage(sendMessage, INLINE);
                    } else if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES+senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    } else {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll());
                        senderMessage(sendMessage, MENU);
                    }
                } else if (currentUser.getState().equals(INLINE)) {
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll());
                        senderMessage(sendMessage, MENU);
                    } else {
                        Category category = categoryService.findAllByName(tx);
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getId()));
                        senderMessage(sendMessage, INLINE);
                    }
                } else if (currentUser.getState().equals(START)) {
                    if (text.equals(senderButtonMessage(BTN_PRODUCtS))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                        break;
                    } else {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!",
                                senderButtonMessage(BTN_PRODUCtS), senderButtonMessage(BTN_ORDERS),
                                BTN_COMMENT_EMOJI + senderButtonMessage(BTN_COMMENT),BTN_SETTING_EMOJI+senderButtonMessage(BTN_SETTING));
                        senderMessage(sendMessage, START);
                    }
                } else if (currentUser.getState().equals(LOCATION)) {
                    if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!",
                                senderButtonMessage(BTN_PRODUCtS), senderButtonMessage(BTN_ORDERS), BTN_COMMENT_EMOJI +
                                        senderButtonMessage(BTN_COMMENT),BTN_SETTING_EMOJI+senderButtonMessage(BTN_SETTING));
                        senderMessage(sendMessage, START);
                    } else if (text.equals(senderButtonMessage(BTN_MY_LOCATION))) {
                        var sendMessage = replyKeyboardButton.myLocation(update, "Yetkazib berish manzilini tanlang", BTN_BACK_EMOJIES +
                                senderButtonMessage(BTN_BACK), addressService.findAllByUserId(currentUser.getId()));
                        senderMessage(sendMessage, MY_LOCATION);
                    } else {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    }
                } else if (currentUser.getState().equals(MY_LOCATION)) {
                    if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    } else {
                        var sendMessage = replyKeyboardButton.myLocation(update, "Yetkazib berish manzilini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), addressService.findAllByUserId(currentUser.getId()));
                        senderMessage(sendMessage, MY_LOCATION);
                    }
                }
                break;
            }
        }
        log.info(update.getMessage().getText());

    }

    private void processLocation(Update update) {
        isChecked = 0;
        var message = update.getMessage();
        message.getLocation().toString();
        Location location = message.getLocation();
        var address = Address.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();

        User user = userService.findOne(currentUser.getId());
        System.out.println(addressService.findByLatLong(user.getId(),address.getLatitude(), address.getLongitude()));
        address.setUsers(user);
        List<Address> allByUserId = addressService.findAllByUserId(user.getId());
        allByUserId.forEach(a -> {
            if (a.getLatitude().equals(address.getLatitude()) && a.getLongitude().equals(address.getLongitude())) {
                isChecked ++;
            }
        });
        if (isChecked == 0) {
            Address address1 = addressService.create(address);
            allByUserId.add(address1);
            currentUser.setAddress(allByUserId);
        }
        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll());
        senderMessage(sendMessage, MENU);
        System.out.println(location.getLongitude() + "," + location.getLatitude());
    }


    private void processNotFound(Message message) {

    }

    private void setView(SendMessage sendMessage) {
        mainBot.sendAnswerMessage(sendMessage);
    }

    private String senderButtonMessage(String text) {
        return bundle.getMessage(text, null, locale);
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
