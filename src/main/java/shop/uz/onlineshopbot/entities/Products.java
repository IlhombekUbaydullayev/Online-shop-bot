package shop.uz.onlineshopbot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String amount;
    private String price;
    private String color;
    @OneToOne
    private FileStorage fileStorage = null;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category = null;
}
