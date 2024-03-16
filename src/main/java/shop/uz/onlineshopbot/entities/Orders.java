package shop.uz.onlineshopbot.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.uz.onlineshopbot.enums.OrdersStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int total;
    private Date orderDate = new Date();
    private String phoneNumber;
    private String fileHashId;
    @Enumerated(EnumType.STRING)
    private OrdersStatus status = OrdersStatus.DELIVERY;
    @OneToOne
    private Address userAddress;
    private Long orderNumber;
    private Long userId;
}
