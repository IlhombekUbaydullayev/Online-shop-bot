package shop.uz.onlineshopbot.utils;

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
import shop.uz.onlineshopbot.entities.Products;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Component
public class InlineKeyboardButtons {

    public SendPhoto orderKeyboards(Update update, String photoUrl, String uploadFolder, Products products) {
        var message = update.getMessage();
        var sendMessage = new SendPhoto();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rows2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUz = new InlineKeyboardButton();
        inlineKeyboardButtonUz.setText(products.getMini() + " ming");
        inlineKeyboardButtonUz.setCallbackData(products.getMini() + "");

       InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText(products.getBig()+" ming");
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

}
