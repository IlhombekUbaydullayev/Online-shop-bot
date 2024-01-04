package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Products;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Long> {
    List<Products> findAllByCategoryId(Long category_id);
}
