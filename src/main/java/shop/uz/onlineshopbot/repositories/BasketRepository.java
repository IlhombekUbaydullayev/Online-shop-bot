package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByChatIdAndDesciptionAndPrice(Long chatId, String desciption,int price);
    Optional<Basket> findByPriceAndDesciptionAndChatIdAndStatusFalse(int price, String desciption, Long chatId);
    Optional<Basket> findByPriceAndDesciptionAndChatId(int price, String desciption, Long chatId);
    Optional<Basket> findByDesciptionAndOrderNameAndChatId(String description,String orderName,Long chatId);
    List<Basket> findAllByChatIdAndStatusTrue(Long chatId);
    List<Basket> deleteByDesciptionAndOrderNameAndChatId(String description,String orderName,Long chatId);

}

