package shop.uz.onlineshopbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import shop.uz.onlineshopbot.bot.MainBot;
import shop.uz.onlineshopbot.entities.*;
import shop.uz.onlineshopbot.enums.UserState;
import shop.uz.onlineshopbot.service.*;
import shop.uz.onlineshopbot.utils.InlineKeyboardButtons;
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

    private final InlineKeyboardButtons inlineKeyboardButton;

    private final FileStorageService fileStorageService;

    private final ProductService productService;

    private final BasketService basketService;
    ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
    Locale locale = null;
    private User currentUser;
    List<Category> allByParentId = null;


    public UpdateController(MessageUtils messageUtils, UserService userService,
                            CategoryService categoryService, AddressService addressService, ReplyKeyboardButton replyKeyboardButton, InlineKeyboardButtons inlineKeyboardButton, FileStorageService fileStorageService, ProductService productService, BasketService basketService) {
        this.messageUtils = messageUtils;
        this.userService = userService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.replyKeyboardButton = replyKeyboardButton;
        this.inlineKeyboardButton = inlineKeyboardButton;
        this.fileStorageService = fileStorageService;
        this.productService = productService;
        this.basketService = basketService;
    }

    public void registerBot(MainBot mainBot) {
        this.mainBot = mainBot;
    }

    public void processUpdate(Update update) throws Exception {
        if (update.hasMessage()) {
            distrubuteMessageType(update);
        } else if (update.hasCallbackQuery()) {
            distrubuteCallbackType(update);
        }
    }

    private void distrubuteCallbackType(Update update) {
        processCallbackMessage(update);
    }

    private void distrubuteMessageType(Update update) throws Exception {
        var message = update.getMessage();
        currentUser = findCurrentUser(update);
        if (message.hasText()) {
            processTextMessage(update);
        } else if (message.hasLocation()) {
            processLocation(update);
        } else if (message.hasContact()) {
            processContact(update);
        } else {
            processNotFound(message);
        }
    }

    private void processTextMessage(Update update) throws FileNotFoundException {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        String text = update.getMessage().getText();
        bundle.setBasenames("text");
        bundle.setDefaultEncoding("UTF-8");
        locale = new Locale("uz");
        if (text.equals("/channel")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(-1002021970016L);
            sendMessage.setText("Yuborildi");
            senderMessage(sendMessage, START);
            
        }
        switch (text) {
            case "/start": {
                var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!",
                        senderButtonMessage(BTN_PRODUCtS), senderButtonMessage(BTN_ORDERS), BTN_COMMENT_EMOJI +
                                senderButtonMessage(BTN_COMMENT), BTN_SETTING_EMOJI + senderButtonMessage(BTN_SETTING));
                senderMessage(sendMessage, START);
                break;
            }
            default: {
                if (currentUser.getState().equals(MENU)) {
                    currentUser.setCheckeds(false);
                    userService.update(currentUser.getId(), currentUser);
                    categoryService.findAll().forEach(c -> {
                        if (text.equals(c.getName())) {
                            currentUser.setTx(c.getName());
                            userService.update(currentUser.getId(), currentUser);
                        }
                    });
                    if (text.equals(currentUser.getTx())) {
                        Category category = categoryService.findAllByName(currentUser.getTx());
                        var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getId()), false);
                        if (category.getFileStorage() != null) {
                            photo(update, category.getFileStorage().getHashId());
                        }
                        senderMessage(sendMessage, INLINE);
                    } else if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    } else {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(), false);
                        senderMessage(sendMessage, MENU);
                    }
                } else if (currentUser.getState().equals(INLINE)) {
                    Category categories = categoryService.findAllByName(text);
                    if (text.equals(categories.getName())) {
                        currentUser.setTx(categories.getName());
                        userService.update(currentUser.getId(), currentUser);
                    }
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(), false);
                        senderMessage(sendMessage, MENU);
                    } else if (text.equals(currentUser.getTx())) {
                        Category category = categoryService.findAllByName(currentUser.getTx());

                        var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(category.getId()), false);
                        photo(update, category.getFileStorage().getHashId());
                        senderMessage(sendMessage, PRODUCTS);
                        currentUser.setCheckeds(false);
                        userService.update(currentUser.getId(), currentUser);
                    } else if (text.equals(BTN_ORDER_EMOJI +" "+ senderButtonMessage(BUTTON_ORDER_SELL))) {
                        List<Basket> findAll = basketService.findAll(update.getMessage().getChatId());
                        // var senderMessageOne = messageUtils.generateSendMessageWithText(update, "Bo'limni tanlan");
                        // senderMessage(senderMessageOne,INLINE);

                        Products products = productService.findByName(currentUser.getTx());
                        Category category = categoryService.findById(products.getCategory().getId());
                        var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                        BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getParentId()), false);
                        senderMessage(sendMessage, INLINE);

                        if (!findAll.isEmpty()) {
                             var senderMessage = inlineKeyboardButton.showBuckets(update, "Bo'limni tanlang",findAll);
                             senderMessage(senderMessage,INLINE);
                        }else {
                               var senderMessages = messageUtils.generateSendMessageWithText(update,"Savatingiz bo'sh.");
                               senderMessage(senderMessages, INLINE);
                        }
                    }
                     else {
                        Category category = categoryService.findAllByName(currentUser.getTx());
                        if (!currentUser.isCheckeds()) {
                            allByParentId = categoryService.findAllByParentId(category.getId());
                        } else {
                            allByParentId = categoryService.findAllByParentId(category.getParentId());
                        }
                        var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), allByParentId, false);
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
                                BTN_COMMENT_EMOJI + senderButtonMessage(BTN_COMMENT), BTN_SETTING_EMOJI + senderButtonMessage(BTN_SETTING));
                        senderMessage(sendMessage, START);
                    }
                } else if (currentUser.getState().equals(LOCATION)) {
                    if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.firstKeyboard(update, "Quyidagilardan birini tanlang!",
                                senderButtonMessage(BTN_PRODUCtS), senderButtonMessage(BTN_ORDERS), BTN_COMMENT_EMOJI +
                                        senderButtonMessage(BTN_COMMENT), BTN_SETTING_EMOJI + senderButtonMessage(BTN_SETTING));
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
                            userService.update(currentUser.getId(), currentUser);
                        }
                    });
                    if (text.equals(BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK))) {
                        var sendMessage = replyKeyboardButton.shareLocation(update, "Joylashuvni kiriting!",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), senderButtonMessage(BTN_LOCATION), senderButtonMessage(BTN_MY_LOCATION));
                        senderMessage(sendMessage, LOCATION);
                    } else if (currentUser.getTx().equals(text)) {
                        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(), false);
                        senderMessage(sendMessage, MENU);
                    } else {
                        var sendMessage = replyKeyboardButton.myLocation(update, "Yetkazib berish manzilini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), addressService.findAllByUserId(currentUser.getId()));
                        senderMessage(sendMessage, MY_LOCATION);
                    }
                } else if (currentUser.getState().equals(PRODUCTS)) {

                    Category categories = categoryService.findAllByName(text);
                    Products products = productService.findByName(text);
                    if (text.equals(categories.getName())) {
                        currentUser.setTx(categories.getName());
                        userService.update(currentUser.getId(), currentUser);
                    } else if (text.equals(products.getName())) {
                        currentUser.setTx(products.getName());
                        userService.update(currentUser.getId(), currentUser);
                    }
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        if (!currentUser.isCheckeds()) {
                            Category category = categoryService.findAllByName(currentUser.getTx());
                            Category category1 = categoryService.findByParentId(category.getParentId());
                            var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                                    BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getParentId()), false);
                            photo(update, category1.getFileStorage().getHashId());
                            senderMessage(sendMessage, INLINE);
                        } else {
                            Products category = productService.findByName(currentUser.getTx());
                            Category category1 = categoryService.findById(category.getCategory().getId());
                            Category category2 = categoryService.findByParentId(category1.getParentId());
                            var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                                    BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category1.getParentId()), false);
                            photo(update, category2.getFileStorage().getHashId());
                            senderMessage(sendMessage, INLINE);
                        }
                        currentUser.setCheckeds(true);
                        userService.update(currentUser.getId(), currentUser);
                    } else if (text.equals(currentUser.getTx())) {
                        Products products1 = productService.findByName(currentUser.getTx());
                        var sendMessage = replyKeyboardButton.secondKeyboards(update, "Quyidagilardan birini tanlang",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), BTN_ORDER_EMOJI + " " + senderButtonMessage(BUTTON_ORDER_SELL), false);
                        senderMessage(sendMessage, ORDER_DEFAULT);
                        uploadPhotoWithInlineKeyboardButton(update, products1.getFileStorage().getHashId(), products);
                    } else {
                        if (!currentUser.isCheckeds()) {
                            Category category = categoryService.findAllByName(currentUser.getTx());

                            var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                    BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(category.getId()), false);
                            photo(update, category.getFileStorage().getHashId());
                            senderMessage(sendMessage, PRODUCTS);
                        } else {
                            Products products1 = productService.findByName(currentUser.getTx());
                            Category category = categoryService.findById(products1.getCategory().getId());

                            var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlari",
                                    BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(products1.getCategory().getId()), false);
                            photo(update, category.getFileStorage().getHashId());
                            senderMessage(sendMessage, PRODUCTS);
                        }
                    }
                } else if (currentUser.getState().equals(ORDER_DEFAULT)) {
                    Products byName = productService.findByName(text);
                    if (text.equals(BTN_BACK_EMOJIES + bundle.getMessage(BTN_BACK, null, locale))) {
                        Products product = productService.findByName(currentUser.getTx());
                        Category category = categoryService.findById(product.getCategory().getId());
                        var sendMessage = replyKeyboardButton.secondKeyboards(update, currentUser.getTx() + " mahsulotlar",
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), productService.findByCategoryId(product.getCategory().getId()), false);
                        senderMessage(sendMessage, PRODUCTS);
                        currentUser.setCheckeds(true);
                        photo(update, category.getFileStorage().getHashId());
                        userService.update(currentUser.getId(), currentUser);
                    } else if (text.equals(BTN_ORDER_EMOJI + " " + senderButtonMessage(BUTTON_ORDER_SELL))) {
                        List<Basket> findAll = basketService.findAll(update.getMessage().getChatId());
                        // var senderMessageOne = messageUtils.generateSendMessageWithText(update, "Bo'limni tanlan");
                        // senderMessage(senderMessageOne,INLINE);

                        Products products = productService.findByName(currentUser.getTx());
                        Category category = categoryService.findById(products.getCategory().getId());
                        var sendMessage = replyKeyboardButton.secondKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                        BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAllByParentId(category.getParentId()), false);
                        senderMessage(sendMessage, INLINE);

                        if (!findAll.isEmpty()) {
                            var senderMessage = inlineKeyboardButton.showBuckets(update, "Bo'limni tanlang",findAll);
                            senderMessage(senderMessage,INLINE);
                       }else {
                              var senderMessages = messageUtils.generateSendMessageWithText(update,"Savatingiz bo'sh.");
                              senderMessage(senderMessages, INLINE);
                       }
                    }
                }
                break;
            }
        }
        log.info(update.getMessage().getText());

    }

    private void processLocation(Update update) {
        currentUser.setIsChecked(0);
        userService.update(currentUser.getId(), currentUser);
        var message = update.getMessage();
        message.getLocation().toString();
        Location location = message.getLocation();
        var address = Address.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();

        User user = userService.findOne(currentUser.getId());
        System.out.println(addressService.findByLatLong(user.getId(), address.getLatitude(), address.getLongitude()));
        address.setUsers(user);
        List<Address> allByUserId = addressService.findAllByUserId(user.getId());
        allByUserId.forEach(a -> {
            if (a.getLatitude().equals(address.getLatitude()) && a.getLongitude().equals(address.getLongitude())) {
                currentUser.setIsChecked(currentUser.getIsChecked() + 1);
                userService.update(currentUser.getId(), currentUser);
            }
        });
        if (currentUser.getIsChecked() == 0) {
            Address address1 = addressService.create(address);
            allByUserId.add(address1);
            currentUser.setAddress(allByUserId);
        }
        var sendMessage = replyKeyboardButton.secondKeyboard(update, "Ro'yxatdan birini tanlang",
                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK), categoryService.findAll(), false);
        senderMessage(sendMessage, MENU);
        System.out.println(location.getLongitude() + "," + location.getLatitude());
    }

    private void processContact(Update update) {
        
    }

    private void processCallbackMessage(Update update) {
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String data = update.getCallbackQuery().getData();
        currentUser = findCurrentUser(chatId);
        Products products = productService.findByName(currentUser.getTx());
        log.info(data);
        if (currentUser.getState().equals(ORDER_DEFAULT)) {
            if (data.equals(products.getMini() + "") || data.equals(products.getBig() + "")) {
                boolean isName = basketService.findByNameAll(Integer.parseInt(data),products.getName(),chatId);
                currentUser.setIsChecked(Integer.parseInt(data));
                userService.update(chatId,currentUser);
                if (isName==false) {
                    var basket = Basket
                        .builder()
                        .desciption(products.getName())
                        .delivery(12000)
                        .chatId(chatId)
                        .price(Integer.parseInt(data))
                        .count(1)
                        .build();
                    if (data.equals(products.getMini()+"")) {
                        basket.setOrderName("Mini");
                    }else {
                        basket.setOrderName("Big");   
                    }
                    basketService.create(basket);
                    var sendMessage = inlineKeyboardButton.orderKeyboardsOrder(update,BTN_ORDER_EMOJI,basket.getCount());
                    editMessage(sendMessage, ORDER);
                }else {
                    Basket basket = basketService.findByNames(Integer.parseInt(data),products.getName(),chatId);
                    basket.setCount(1);
                    basketService.update(chatId,basket,currentUser.getIsChecked());
                    var sendMessage = inlineKeyboardButton.orderKeyboardsOrder(update,BTN_ORDER_EMOJI,basket.getCount());
                    editMessage(sendMessage, ORDER);  
                }
            }
        }
        else if(currentUser.getState().equals(ORDER)) {
            if (data.equals(BTN_ORDER_EMOJI)) {
                Basket basket = basketService.findByNames(currentUser.getIsChecked(), products.getName(), chatId);
                basket.setStatus(true);
                basket.setTotal(basket.getCount()+basket.getTotal());
                basketService.update(chatId,basket, currentUser.getIsChecked());
                Products productsSec = productService.findByName(currentUser.getTx());
                Category category = categoryService.findById(productsSec.getCategory().getId());
                List<Category> findAll = categoryService.findAllByParentId(category.getParentId()); 
                Category second = categoryService.findById(category.getParentId());
                        var sendMessage = replyKeyboardButton.bucketKeyboard(update,senderButtonMessage(CHECK_PRODUCTS),
                                BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK),findAll,BTN_ORDER_EMOJI +" "+ senderButtonMessage(BUTTON_ORDER_SELL));
                        if (products.getFileStorage() != null) {
                            photoBack(update,second.getFileStorage().getHashId());
                        }
                        senderMessage(sendMessage, INLINE);


            } else if (data.equals("add")) {
                Basket basket = basketService.findByNames(currentUser.getIsChecked(), products.getName(), chatId);
                if (basket.getCount() < 21) {
                    basket.setCount(basket.getCount()+1);
                    basketService.update(chatId,basket, currentUser.getIsChecked());
                    var sendMessage = inlineKeyboardButton.orderKeyboardsOrder(update,BTN_ORDER_EMOJI,basket.getCount());
                    editMessage(sendMessage,ORDER);
                }
            }else if (data.equals("remove")) {
                
                Basket basket = basketService.findByNames(currentUser.getIsChecked(), products.getName(), chatId);
                if (basket.getCount() > 1) {
                    basket.setCount(basket.getCount()-1);
                    basketService.update(chatId,basket, currentUser.getIsChecked());
                    var sendMessage = inlineKeyboardButton.orderKeyboardsOrder(update,BTN_ORDER_EMOJI,basket.getCount());
                    editMessage(sendMessage,ORDER);
                }
            }
        }else if(currentUser.getState().equals(INLINE)) {
            String[] arrOfStr = data.split(" ");
            System.out.println(arrOfStr.length);
            if (data.equals(BTN_KURER_EMOJI + " Buyurtma berish")) {
                var sendMessage = replyKeyboardButton.myContact(update,senderButtonMessage(TEXT_SEND_CONTACT), BTN_SHARE_CONTACT+ " " +senderButtonMessage(MY_PHONE_TEX), BTN_BACK_EMOJIES + senderButtonMessage(BTN_BACK));
                senderMessage(sendMessage, INLINE);
            }else if (arrOfStr.length == 3) {
                basketService.deleteByName(arrOfStr[0] + " " + arrOfStr[1],arrOfStr[2], chatId);
                List<Basket> findAll = basketService.findAll(chatId);
                
                if (!findAll.isEmpty()) {
                    var senderMessage = inlineKeyboardButton.deleteKeyboardsOrder(update,findAll);
                    editMessage(senderMessage, INLINE);
                }else {
                    var senderMessages = inlineKeyboardButton.deleteEmptyKeyboardsOrder(update,findAll);
                    editMessage(senderMessages, INLINE);
                }
            }
        }
    }


    private void processNotFound(Message message) {

    }

    private void setView(SendMessage sendMessage) {
        mainBot.sendAnswerMessage(sendMessage);
    }

    private void setView(EditMessageReplyMarkup sendMessage) {
        mainBot.sendAnswerMessage(sendMessage);
    }

    private void setView(EditMessageText sendMessage) {
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

    private void editMessage(EditMessageReplyMarkup sendMessage, UserState state) {
        setView(sendMessage);
        currentUser.setState(state);
        userService.update(currentUser.getId(), currentUser);
    }

    private void editMessage(EditMessageText sendMessage, UserState state) {
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

    private User findCurrentUser(Long chatId) {
        for (User currentUser : userService.findAll()) {
            if (chatId.equals(currentUser.getChatId())) {
                return currentUser;
            }
        }
        User user1 = new User();
        user1.setChatId(chatId);
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

    private void photoBack(Update update, String hashId) {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        if (fileStorage != null) {
            SendPhoto messages = new SendPhoto();
            messages.setChatId(update.getCallbackQuery().getFrom().getId());
            messages.setPhoto(new InputFile(new File(uploadFolder + fileStorage.getUploadPath())));
            mainBot.sendAnswerMessageWithPhoto(messages);
        }
    }

    private void uploadPhotoWithInlineKeyboardButton(Update update, String products1, Products products) {
        FileStorage fileStorage = fileStorageService.findByHashId(products1);
        var senderMessage2 = inlineKeyboardButton.orderKeyboards(update, fileStorage.getUploadPath(), uploadFolder, products);
        mainBot.sendAnswerMessageWithPhoto(senderMessage2);
    }
}
