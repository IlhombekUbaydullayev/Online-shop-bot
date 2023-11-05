package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category categories) {
        return repository.save(categories);
    }

    public List<Category> findAll() {
        return repository.findAllByParentIdNull();
    }
    public Category findAllByName(String name) {
        return repository.findByName(name).get();
    }

    public List<Category> findAllByParentId(Long parentId) {
        return repository.findAllByParentId(parentId);
    }
}
