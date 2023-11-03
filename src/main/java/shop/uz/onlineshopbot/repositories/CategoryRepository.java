package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
