package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.uz.onlineshopbot.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Modifying
    @Query("delete from Orders o where o.userId=:userId")
    void deleteOrders(@Param("userId") Long userId);
}
