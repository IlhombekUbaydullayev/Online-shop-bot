package shop.uz.onlineshopbot.utils;

import org.glassfish.jersey.internal.guava.HashMultimap;
import org.glassfish.jersey.internal.guava.Multimap;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import shop.uz.onlineshopbot.entities.Basket;
import shop.uz.onlineshopbot.entities.Products;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import static java.lang.Math.toIntExact;

@Component
public class InlineKeyboardButtons {

    String text2 = "";
    int count = 0;
    int total = 0;

    public SendPhoto orderKeyboards(Update update, String photoUrl, String uploadFolder, Products products) {
        var message = update.getMessage();
        var sendMessage = new SendPhoto();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rows2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUz = new InlineKeyboardButton();
        inlineKeyboardButtonUz.setText("mini " +products.getMini() + " ming");
        inlineKeyboardButtonUz.setCallbackData(products.getMini() + "");

       InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText("big " +products.getBig()+" ming");
        inlineKeyboardButtonRu.setCallbackData(products.getBig() + "");
        rows2.add(inlineKeyboardButtonUz);
        rows2.add(inlineKeyboardButtonRu);

        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        sendMessage.setPhoto(new InputFile(new File(uploadFolder + photoUrl)));
        tr.add(rows2);
        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }

    public EditMessageReplyMarkup orderKeyboardsOrder(Update update,String prev,int count) {
        var message = update.getMessage();
        var sendMessage = new EditMessageReplyMarkup();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rows2 = new ArrayList<>();
        List<InlineKeyboardButton> rows3 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUz = new InlineKeyboardButton();
        inlineKeyboardButtonUz.setText("-");
        inlineKeyboardButtonUz.setCallbackData("remove");

        InlineKeyboardButton inlineKeyboardButtonNum = new InlineKeyboardButton();
        inlineKeyboardButtonNum.setText(""+count);
        inlineKeyboardButtonNum.setCallbackData("num");

        InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText("+");
        inlineKeyboardButtonRu.setCallbackData("add");

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(prev);
        inlineKeyboardButton.setCallbackData(prev);
        rows2.add(inlineKeyboardButtonUz);
        rows2.add(inlineKeyboardButtonNum);
        rows2.add(inlineKeyboardButtonRu);
        rows3.add(inlineKeyboardButton);

        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(rows2);
        tr.add(rows3);
        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId());
        sendMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
        return sendMessage;
    }


    public SendMessage showBuckets(Update update,String sender,List<Basket> baskets) {
        text2 = "";
        count = 0;
        total = 0;
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        baskets.forEach(f -> {
            List<InlineKeyboardButton> btn = new ArrayList<>();
            InlineKeyboardButton line1 = new InlineKeyboardButton();
            total += f.getTotal();
            switch(f.getTotal()) {
                case 1 : {
                    text2 +=  "1️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 2 : {
                    text2 +=  "2️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 3 : {
                    text2 +=  "3️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 4 : {
                    text2 +=  "4️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 5 : {
                    text2 +=  "5️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 6 : {
                    text2 +=  "6️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 7 : {
                    text2 +=  "7️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 8 : {
                    text2 +=  "8️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 9 : {
                    text2 +=  "9️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 10 : {
                    text2 +=  "🔟";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 11 : {
                    text2 +=  "1️⃣1️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 12 : {
                    text2 +=  "1️⃣2️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 13 : {
                    text2 +=  "1️⃣3️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 14 : {
                    text2 +=  "1️⃣4️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 15 : {
                    text2 +=  "1️⃣5️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 16 : {
                    text2 +=  "1️⃣6️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 17 : {
                    text2 +=  "1️⃣7️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 18 : {
                    text2 +=  "1️⃣8️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 19 : {
                    text2 +=  "1️⃣9️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 20 : {
                    text2 +=  "2️⃣0️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
            }
            text2 += " ✖️ ";
            text2 += f.getDesciption();
            text2 += " ";
            text2 += f.getOrderName();
            text2 += "\n";
            line1.setText("❌"+f.getDesciption() + " " + f.getOrderName());
            line1.setCallbackData(f.getDesciption()+" "+f.getOrderName());
            btn.add(line1);
            rows.add(btn);
        });
        text2 += "Mahsulotlar : " + count + " so'm\n";
        if (total < 5) {
            text2 += "Yetkazib berish : 10000 so'm\n";
            text2 += "Jami: " + (count + 10000) + " so'm\n";
        }else {
            text2 += "Yetkazib berish : tekin\n";
            text2 += "Jami: " + count + " so'm\n";
        }
        inlineKeyboardMarkup.setKeyboard(rows);
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Savatda:\n"+text2);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public EditMessageText deleteKeyboardsOrder(Update update,List<Basket> baskets) {
        text2 = "";
        count = 0;
        total = 0;
        var sendMessage = new EditMessageText();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        baskets.forEach(f -> {
            List<InlineKeyboardButton> btn = new ArrayList<>();
            InlineKeyboardButton line1 = new InlineKeyboardButton();
            switch(f.getTotal()) {
                case 1 : {
                    text2 +=  "1️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 2 : {
                    text2 +=  "2️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 3 : {
                    text2 +=  "3️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 4 : {
                    text2 +=  "4️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 5 : {
                    text2 +=  "5️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 6 : {
                    text2 +=  "6️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 7 : {
                    text2 +=  "7️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 8 : {
                    text2 +=  "8️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 9 : {
                    text2 +=  "9️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 10 : {
                    text2 +=  "🔟";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 11 : {
                    text2 +=  "1️⃣1️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 12 : {
                    text2 +=  "1️⃣2️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 13 : {
                    text2 +=  "1️⃣3️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 14 : {
                    text2 +=  "1️⃣4️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 15 : {
                    text2 +=  "1️⃣5️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 16 : {
                    text2 +=  "1️⃣6️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 17 : {
                    text2 +=  "1️⃣7️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 18 : {
                    text2 +=  "1️⃣8️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 19 : {
                    text2 +=  "1️⃣9️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
                case 20 : {
                    text2 +=  "2️⃣0️⃣";
                    count += f.getTotal()*f.getPrice();
                    break;
                }
            }
            text2 += " ✖️ ";
            text2 += f.getDesciption();
            text2 += " ";
            text2 += f.getOrderName();
            text2 += "\n";
            line1.setText("❌"+f.getDesciption() + " " + f.getOrderName());
            line1.setCallbackData(f.getDesciption()+" "+f.getOrderName());
            btn.add(line1);
            rows.add(btn);
        });
        text2 += "Mahsulotlar : " + count + " so'm\n";
        if (total < 5) {
            text2 += "Yetkazib berish : 10000 so'm\n";
            text2 += "Jami: " + (count + 10000) + " so'm\n";
        }else {
            text2 += "Yetkazib berish : tekin\n";
            text2 += "Jami: " + count + " so'm\n";
        }
        inlineKeyboardMarkup.setKeyboard(rows);
        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId());
        sendMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setText(text2);
        return sendMessage;
    }

    public EditMessageText deleteEmptyKeyboardsOrder(Update update,List<Basket> baskets) {
        text2 = "";
        count = 0;
        var sendMessage = new EditMessageText();
        sendMessage.setChatId(update.getCallbackQuery().getFrom().getId());
        sendMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
        sendMessage.setText("Savatingiz bo'sh");
        return sendMessage;
    }

    

}
