package shop.uz.onlineshopbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import shop.uz.onlineshopbot.bot.MainBot;
import shop.uz.onlineshopbot.entities.Address;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.FileStorage;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.enums.UserState;
import shop.uz.onlineshopbot.service.*;
import shop.uz.onlineshopbot.utils.MessageUtils;
import shop.uz.onlineshopbot.utils.ReplyKeyboardButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import static shop.uz.onlineshopbot.enums.UserState.*;
import static shop.uz.onlineshopbot.utils.Emojies.*;
import static shop.uz.onlineshopbot.utils.TranslationCode.*;

@Component
@Slf4j
public class UpdateController {

    @Value("${upload.folder}")
    private String uploadFolder;
    private MainBot mainBot;
    private final MessageUtils messageUtils;

    private final UserService userService;

    private final CategoryService categoryService;

    private final AddressService addressService;

    private final ReplyKeyboardButton replyKeyboardButton;

    private final FileStorageService fileStorageService;

    private final ProductService productService;
    ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
    Locale locale = null;
    private User currentUser;
    List<Category> allByParentId = null;

    public UpdateController(MessageUtils messageUtils, UserService userService,
                            CategoryService categoryService, AddressService addressService, ReplyKeyboardButton replyKeyboardButton, FileStorageService fileStorageService, ProductService productService) {
        this.messageUtils = messageUtils;
        this.userService = userService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.replyKeyboardButton = replyKeyboardButton;
        this.fileStorageService = fileStorageService;
        this.productService = productService;
    }

    public void registerBot(MainBot mainBot) {
        this.mainBot = mainBot;
    }

    public void processUpdate(Update update) throws Exception {
        if (update == null) {
            return;
        }
        if (update.getMessage() != null) {
            distrubuteMessageType(update);
        }
    }

    private void distrubuteMessageType(Update update) throws Exception {
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

    private void processTextMessage(Update update) throws FileNotFoundException {
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
                    currentUser.setCheckeds(false);
                    userService.update(currentUser.getId(),currentUser);
                    categoryService.findAll().forEach(c -> {
                        if (text.equals(c.getName())) {
                            currentUser.setTx(c.getName());
                            userService.update(currentUser.getId(),currentUser);
                        }
                    });
                    if (text.equals(currentUser.getTx())) {
                        Category category = categoryService.findAllByName(currentUser.getTx());
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getId()),false);
                        if (category.getFileStorage() != null) {
                            photo(update, category.getFileStorage().getHashId());
                        }
                        senderMessage(sendMessage, INLINE);
                    } else if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES+senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    } else {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(),false);
                        senderMessage(sendMessage, MENU);
                    }
                } else if (currentUser.getState().equals(INLINE)) {
                    Category categories = categoryService.findAllByName(text);
                    if (text.equals(categories.getName())) {
                        currentUser.setTx(categories.getName());
                        userService.update(currentUser.getId(),currentUser);
                    }
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(),false);
                        senderMessage(sendMessage, MENU);
                    }else if (text.equals(currentUser.getTx())){
                        Category category = categoryService.findAllByName(currentUser.getTx());

                        var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(category.getId()),false);
                        photo(update, category.getFileStorage().getHashId());
                        senderMessage(sendMessage, PRODUCTS);
                    }else {
                        Category category = categoryService.findAllByName(currentUser.getTx());
                        if (!currentUser.isCheckeds()) {
                            allByParentId = categoryService.findAllByParentId(category.getId());
                        }else {
                            allByParentId = categoryService.findAllByParentId(category.getParentId());
                        }
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK),allByParentId,false);
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
                    addressService.findAllByUserId(currentUser.getId()).forEach(a -> {
                        if (text.equals(a.getLatitude().toString())) {
                            currentUser.setTx(text);
                            userService.update(currentUser.getId(),currentUser);
                        }
                    });
                    if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    }else if (currentUser.getTx().equals(text)) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(),false);
                        senderMessage(sendMessage, MENU);
                    }
                    else {
                        var sendMessage = replyKeyboardButton.myLocation(update, "Yetkazib berish manzilini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), addressService.findAllByUserId(currentUser.getId()));
                        senderMessage(sendMessage, MY_LOCATION);
                    }
                }else if (currentUser.getState().equals(PRODUCTS)) {
                    Category categories = categoryService.findAllByName(text);
                    if (text.equals(categories.getName())) {
                        currentUser.setTx(categories.getName());
                        userService.update(currentUser.getId(),currentUser);
                    }
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        Category category = categoryService.findAllByName(currentUser.getTx());
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Mahsulotni tanlang!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getParentId()),false);
                        senderMessage(sendMessage, INLINE);
                        currentUser.setCheckeds(true);
                        userService.update(currentUser.getId(),currentUser);
                    }else if (text.equals(currentUser.getTx())){
                        Category category = categoryService.findAllByName(currentUser.getTx());

                        var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(category.getId()),false);
                        photo(update, category.getFileStorage().getHashId());
                        senderMessage(sendMessage, PRODUCTS);
                    }else {
                        Category category = categoryService.findAllByName(currentUser.getTx());

                        var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(category.getId()),false);
                        photo(update, category.getFileStorage().getHashId());
                        senderMessage(sendMessage, PRODUCTS);
                    }
                }
                break;
            }
        }
        log.info(update.getMessage().getText());

    }

    private void processLocation(Update update) {
        currentUser.setIsChecked(0);
        userService.update(currentUser.getId(),currentUser);
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
                currentUser.setIsChecked(currentUser.getIsChecked()+1);
                userService.update(currentUser.getId(),currentUser);
            }
        });
        if (currentUser.getIsChecked() == 0) {
            Address address1 = addressService.create(address);
            allByUserId.add(address1);
            currentUser.setAddress(allByUserId);
        }
        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(),false);
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

    private void photo(Update update, String hashId) {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        if (fileStorage != null) {
            SendPhoto messages = new SendPhoto();
            messages.setChatId(update.getMessage().getChatId());
            messages.setPhoto(new InputFile(new File(uploadFolder + fileStorage.getUploadPath())));
            mainBot.sendAnswerMessageWithPhoto(messages);
        }
    }
}
