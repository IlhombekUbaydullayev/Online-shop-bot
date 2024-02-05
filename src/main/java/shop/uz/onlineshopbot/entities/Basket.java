package shop.uz.onlineshopbot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desciption;
    private int price;
    private int delivery;
    private int total;
    private Long chatId;
    private boolean status = false;
    private int count = 1;
}
