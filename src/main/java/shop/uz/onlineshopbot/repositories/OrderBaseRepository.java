package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.uz.onlineshopbot.entities.OrderBase;

public interface OrderBaseRepository extends JpaRepository<OrderBase,Long> {

}
