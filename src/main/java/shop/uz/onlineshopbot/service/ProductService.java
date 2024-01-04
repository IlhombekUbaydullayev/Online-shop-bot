package shop.uz.onlineshopbot.service;


import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.FileStorage;
import shop.uz.onlineshopbot.entities.Products;
import shop.uz.onlineshopbot.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final FileStorageService fileStorageService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository repository, FileStorageService fileStorageService,
                          CategoryService categoryService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
        this.categoryService = categoryService;
    }

    public Products create(Products products, String hashId, Long categoryId) {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        Category category = categoryService.findById(categoryId);
        products.setFileStorage(fileStorage);
        products.setCategory(category);
        Products save = repository.save(products);
        return save;
    }

    public Products update(Products products) {
        return null;
    }
    
    public List<Products> findByCategoryId(Long id) {
        return repository.findAllByCategoryId(id);
    }
}
