package shop.uz.onlineshopbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import shop.uz.onlineshopbot.entities.OrderBase;
import shop.uz.onlineshopbot.repositories.OrderBaseRepository;

@Service
public class OrderBaseService {
    private final OrderBaseRepository repository;

    public OrderBaseService(OrderBaseRepository repository) {
        this.repository = repository;
    }

    public OrderBase create(OrderBase orderBase) {
        return repository.save(orderBase);
    }

    public List<OrderBase> findAll() {
        return repository.findAll();
    }
}
