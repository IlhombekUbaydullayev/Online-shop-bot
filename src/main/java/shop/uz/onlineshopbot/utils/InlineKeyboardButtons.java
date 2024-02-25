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
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> btn = new ArrayList<>();
        InlineKeyboardButton line1 = new InlineKeyboardButton();
        Set<String> findAll = new HashSet<String>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        
        for(int i = 1;i <= baskets.size()-1; i++ ) {
            map.put(i, baskets.get(i).getDesciption());
        }

        Multimap<String, Integer> multiMap = HashMultimap.create();
        for (Entry<Integer, String> entry : map.entrySet()) {
            multiMap.put(entry.getValue(), entry.getKey());
        }
        System.out.println();
        for (Entry<String, Collection<Integer>> entry : multiMap.asMap().entrySet()) {
            System.out.println("Original value: " + entry.getKey() + " was mapped to keys: "+ entry.getValue());
        }

        findAll.forEach(f -> {
            text2 += f;
            text2 += "\n";
        });
        line1.setText("Savatda:\n");
        line1.setCallbackData("order");
        btn.add(line1);
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(btn);
        inlineKeyboardMarkup.setKeyboard(rows);
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Savatda:\n"+text2);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

}
