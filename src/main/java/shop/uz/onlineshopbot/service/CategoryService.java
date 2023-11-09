package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

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

    public List<Category> findAllNotBull() {
        return repository.findAllByParentIdNull();
    }

    public Category findAllByName(String name) {
        Optional<Category> byName = repository.findByName(name);
        if (byName.isPresent()) {
            return byName.get();
        }
        return new Category();
    }

    public List<Category> findAllByParentId(Long parentId) {
        return repository.findAllByParentId(parentId);
    }

    public Category update(Long categoryId,Category category) {
        Optional<Category> category1 = repository.findById(categoryId);
        if (category1.isPresent()) {
            Category category2 = category1.get();
            category2.setName(category.getName());
            category2.setFileStorage(category.getFileStorage());
            repository.save(category2);
            return category2;
        }
        return new Category();
    }

    public Category findById(Long id) {
        return repository.findById(id).get();
    }
}
