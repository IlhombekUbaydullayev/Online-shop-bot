package shop.uz.onlineshopbot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import shop.uz.onlineshopbot.entities.Address;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.Products;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static shop.uz.onlineshopbot.utils.TranslationCode.BTN_BACK;

@Component
public class ReplyKeyboardButton {

    int count = 2;
    public SendMessage firstKeyboard(
            Update update, String text,String btnText,String orders,String comment,String setting) {
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow kr1 = new KeyboardRow();
        KeyboardRow kr2 = new KeyboardRow();
        KeyboardRow kr3 = new KeyboardRow();
        KeyboardButton btn1 = new KeyboardButton();
        KeyboardButton btn2 = new KeyboardButton();
        KeyboardButton btn3 = new KeyboardButton();
        KeyboardButton btn4 = new KeyboardButton();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        btn1.setText(btnText);
        btn2.setText(orders);
        btn3.setText(comment);
        btn4.setText(setting);
        kr1.add(btn1);
        kr2.add(btn2);
        kr3.add(btn3);
        kr3.add(btn4);
        rows.add(kr1);
        rows.add(kr2);
        rows.add(kr3);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage secondKeyboard(Update update, String text,String btnText, List<Category> categories,boolean isPhoto) {
        count = 0;
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        sendMessage.setChatId(message.getChatId());
        KeyboardRow kr2 = new KeyboardRow();
        sendMessage.setText(text);
        categories.forEach(c -> {
            KeyboardButton btn1 = new KeyboardButton();
            btn1.setText(c.getName());
            kr2.add(btn1);
            if (count % 2 == 1) {
                KeyboardRow kr1 = new KeyboardRow();
                for (int i = count-1; i < kr2.size(); i++) {
                    kr1.add(kr2.get(i));
                }
                rows.add(kr1);
            }
            count++;
        });
        if (kr2.size() % 2 != 0) {
            KeyboardRow kr1 = new KeyboardRow();
            kr1.add(kr2.get(kr2.size()-1));
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }else {
            KeyboardRow kr1 = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage secondKeyboards(Update update, String text, String btnText, List<Products> categories, boolean isPhoto) {
        count = 0;
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        sendMessage.setChatId(message.getChatId());
        KeyboardRow kr2 = new KeyboardRow();
        sendMessage.setText(text);
        categories.forEach(c -> {
            KeyboardButton btn1 = new KeyboardButton();
            btn1.setText(c.getName());
            kr2.add(btn1);
            if (count % 2 == 1) {
                KeyboardRow kr1 = new KeyboardRow();
                for (int i = count-1; i < kr2.size(); i++) {
                    kr1.add(kr2.get(i));
                }
                rows.add(kr1);
            }
            count++;
        });
        if (kr2.size() % 2 != 0) {
            KeyboardRow kr1 = new KeyboardRow();
            kr1.add(kr2.get(kr2.size()-1));
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }else {
            KeyboardRow kr1 = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }
        System.out.println(kr2.size());
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage secondKeyboards(Update update, String text, String btnText,String emoji,boolean isPhoto) {
        count = 0;
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("<i>" + text + "</i>");
        KeyboardRow kr1 = new KeyboardRow();
        KeyboardRow kr2 = new KeyboardRow();
        KeyboardButton btn = new KeyboardButton();
        KeyboardButton btn1 = new KeyboardButton();
        btn.setText(btnText);
        btn1.setText(emoji);
        kr1.add(btn);
        kr2.add(btn1);
        rows.add(kr2);
        rows.add(kr1);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.enableHtml(true);
//        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    public SendMessage shareLocation(Update update,String text,String btn,String btnGeolocation,String myLocation) {
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow kr1 = new KeyboardRow();
        KeyboardRow back = new KeyboardRow();
        KeyboardButton MyLocation = new KeyboardButton();
        KeyboardButton backButton = new KeyboardButton();
        KeyboardButton geoLocation = new KeyboardButton();
        MyLocation.setText(myLocation);
        backButton.setText(btn);
        geoLocation.setText(btnGeolocation);
        kr1.add(MyLocation);
        back.add(geoLocation);
        back.add(backButton);
        rows.add(kr1);
        rows.add(back);
        geoLocation.setRequestLocation(true);
        sendMessage.setText(text);
        sendMessage.setChatId(message.getChatId());
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage myLocation(Update update, String text,String btnText, List<Address> addresses) {
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        addresses.forEach(c -> {
            KeyboardRow kr2 = new KeyboardRow();
            KeyboardButton btn1 = new KeyboardButton();
            btn1.setText(c.getLatitude().toString());
            kr2.add(btn1);
            rows.add(kr2);
        });
        KeyboardRow kr1 = new KeyboardRow();
        KeyboardButton btn = new KeyboardButton();
        btn.setText(btnText);
        kr1.add(btn);
        rows.add(kr1);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }


    public SendMessage bucketKeyboard(Update update, String text,String btnText, List<Category> categories,String isOrder) {
        count = 0;
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId());
        KeyboardRow kr2 = new KeyboardRow();
        KeyboardRow kr3 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText(isOrder);
        kr3.add(button);
        rows.add(kr3);
        sendMessage.setText(text);
        categories.forEach(c -> {
            KeyboardButton btn1 = new KeyboardButton();
            btn1.setText(c.getName());
            kr2.add(btn1);
            if (count % 2 == 1) {
                KeyboardRow kr1 = new KeyboardRow();
                for (int i = count-1; i < kr2.size(); i++) {
                    kr1.add(kr2.get(i));
                }
                rows.add(kr1);
            }
            count++;
        });
        if (kr2.size() % 2 != 0) {
            KeyboardRow kr1 = new KeyboardRow();
            kr1.add(kr2.get(kr2.size()-1));
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }else {
            KeyboardRow kr1 = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();
            btn.setText(btnText);
            kr1.add(btn);
            rows.add(kr1);
        }
        System.out.println(kr2.size());
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage myContact(Update update, String text,String btnText1,String btnText2) {
    
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardButton btn1 = new KeyboardButton();
        btn1.setText(btnText1); 
        btn1.setRequestContact(true);
        KeyboardRow kr1 = new KeyboardRow();
        KeyboardRow kr2 = new KeyboardRow();
        KeyboardButton btn2 = new KeyboardButton();
        btn2.setText(btnText2); 
        kr1.add(btn1);
        kr2.add(btn2);
        rows.add(kr1);
        rows.add(kr2);
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        sendMessage.setText(text);
        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId());
        return sendMessage;
    }


    public SendMessage sendOrder(Update update,String text,String btnText) {
        var sendMessage = new SendMessage();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> row = new ArrayList();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton btn = new KeyboardButton();
        btn.setText(btnText);
        sendMessage.setText(text);
        keyboardRow.add(btn);
        row.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(row);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setChatId(update.getMessage().getChatId());
        return sendMessage;
    }
}
