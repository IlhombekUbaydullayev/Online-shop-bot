package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.uz.onlineshopbot.entities.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByChatIdAndDesciptionAndPrice(Long chatId, String desciption,int price);
    Optional<Basket> findByPriceAndDesciptionAndChatIdAndStatusFalse(int price, String desciption, Long chatId);
    Optional<Basket> findByPriceAndDesciptionAndChatId(int price, String desciption, Long chatId);
    Optional<Basket> findByDesciptionAndOrderNameAndChatId(String description,String orderName,Long chatId);
    List<Basket> findAllByChatIdAndStatusTrue(Long chatId);
    @Modifying
    @Query("delete from Basket b where b.chatId=:chatId")
    void deleteBasket(@Param("chatId") Long chatId);
}

