package shop.uz.onlineshopbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
@ComponentScan(basePackages = {
        "shop.uz.onlineshopbot",
        "org.telegram.telegrambots"
})
public class OnlineShopBotApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(OnlineShopBotApplication.class, args);
    }

}
