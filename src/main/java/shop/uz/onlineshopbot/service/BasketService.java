package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Basket;
import shop.uz.onlineshopbot.repositories.BasketRepository;

import java.util.Optional;

@Service
public class BasketService {
    private final BasketRepository repository;

    public BasketService(BasketRepository repository) {
        this.repository = repository;
    }

    public Basket create(Basket basket) {
        return repository.save(basket);
    }

    public Basket update(Long chatId,Basket basket,int price) {
        Optional<Basket> byBasket = repository.findByChatIdAndDesciptionAndPriceAndStatusFalse(chatId, basket.getDesciption(),price);
        if (byBasket.isPresent()) {
            Basket bs = byBasket.get();
            bs.setStatus(basket.isStatus());
            bs.setCount(basket.getCount());
            return repository.save(bs);
        }
        return new Basket();
    }

    public boolean findByName(int data,String name, Long chatId) {
        return repository.findByPriceAndDesciptionAndChatIdAndStatusFalse(data,name,chatId).isPresent();
    }

    public Basket findByNames(int data,String name, Long chatId) {
        return repository.findByPriceAndDesciptionAndChatIdAndStatusFalse(data,name,chatId).get();
    }
}
