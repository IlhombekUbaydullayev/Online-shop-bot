package shop.uz.onlineshopbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.FileStorage;
import shop.uz.onlineshopbot.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;
    private final FileStorageService fileStorageService;

    public CategoryService(CategoryRepository repository, FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    public Category create(Category categories,String hashId) {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        if (fileStorage != null) {
            log.info("This is : " + fileStorage);
            categories.setFileStorage(fileStorage);
        }
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
