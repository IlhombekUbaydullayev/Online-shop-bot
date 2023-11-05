package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByParentIdNull();
    Optional<Category> findByName(String name);
    List<Category> findAllByParentId(Long parentId);
}
