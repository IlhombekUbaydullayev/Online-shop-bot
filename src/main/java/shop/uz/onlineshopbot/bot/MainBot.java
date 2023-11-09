package shop.uz.onlineshopbot.bot;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import shop.uz.onlineshopbot.controller.UpdateController;

@Slf4j
@Component
public class MainBot extends TelegramLongPollingBot {

    public static String botUserName;

    public static String botToken;

    @Value("${bot.token}")
    public void setBotToken(String bToken) {
        botToken = bToken;
    }

    @Value("${bot.name}")
    public void setBotUserName(String bUserName) {
        botUserName = bUserName;
    }

    private UpdateController updateController;

    public MainBot(UpdateController updateController) {
        this.updateController = updateController;
    }


    @PostConstruct
    public void init() {
        updateController.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            updateController.processUpdate(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void sendAnswerMessage(SendMessage sendMessage) {
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            }catch (Exception e) {
                log.error(String.valueOf(e));
            }
        }
    }

    public void sendAnswerMessageWithPhoto(SendPhoto sendPhoto) {
        if (sendPhoto != null) {
            try {
                this.execute(sendPhoto);
            }catch (Exception e) {
                log.error(String.valueOf(e));
            }
        }
    }
}
