package shop.uz.onlineshopbot.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String amount;
    private String price;
    private String color;
    private int mini;
    private int big;
    @OneToOne
    private FileStorage fileStorage = null;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category = null;
}
