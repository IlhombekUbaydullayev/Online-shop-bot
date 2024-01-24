package shop.uz.onlineshopbot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardButtons {

    public SendMessage orderKeyboards(Update update) {
        var message = update.getMessage();
        var sendMessage = new SendMessage();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rows2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUz = new InlineKeyboardButton();
        inlineKeyboardButtonUz.setText("\uD83C\uDDFA\uD83C\uDDFF Uz");
        inlineKeyboardButtonUz.setCallbackData("UZ_SELECt");

       InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText("\uD83C\uDDF7\uD83C\uDDFA Ru");
        inlineKeyboardButtonRu.setCallbackData("RU_SELECt");

        rows2.add(inlineKeyboardButtonUz);
        rows2.add(inlineKeyboardButtonRu);

        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(rows2);
        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(".");
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }

}
