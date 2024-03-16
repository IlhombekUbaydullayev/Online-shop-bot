package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.uz.onlineshopbot.entities.Orders;
import shop.uz.onlineshopbot.repositories.OrderRepository;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository repository;
    
    public OrderService(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }

    public Orders create(Orders orders) {
        return repository.save(orders);
    }

    @Transactional
    public String deleteAllByChatId(Long chatId) {
        repository.deleteOrders(chatId);
        return "";
    }
}
