package shop.uz.onlineshopbot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId = null;
    @OneToOne
    private FileStorage fileStorage = null;
    @OneToMany( mappedBy = "category")
    private List<Products> products = null;
}
