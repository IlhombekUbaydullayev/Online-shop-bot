package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Products;

public interface ProductRepository extends JpaRepository<Products,Long> {

}
