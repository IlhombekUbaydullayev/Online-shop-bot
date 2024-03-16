package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.uz.onlineshopbot.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    
}
