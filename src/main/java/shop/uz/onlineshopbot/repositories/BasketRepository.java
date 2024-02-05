package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Basket;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByChatIdAndDesciptionAndPriceAndStatusFalse(Long chatId, String desciption,int price);
    Optional<Basket> findByPriceAndDesciptionAndChatIdAndStatusFalse(int price, String desciption, Long chatId);

}

