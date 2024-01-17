package shop.uz.onlineshopbot.service;


import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.dto.ProductsUpdate;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.entities.FileStorage;
import shop.uz.onlineshopbot.entities.Products;
import shop.uz.onlineshopbot.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

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

    public Products update(ProductsUpdate update,Long id) {
        Optional<Products> byId = repository.findById(id);
        if (byId.isPresent()) {
            var product = byId.get();
            product.setName(update.name);
            product.setBig(update.big);
            product.setColor(update.color);
            product.setMini(update.mini);
            product.setAmount(update.amount);
            product.setPrice(update.price);
            product.setFileStorage(fileStorageService.findByHashId(update.fileStorageHashId));
            product.setCategory(categoryService.findById(update.categoryId));
            repository.save(product);
            return product;
        }
        return new Products();
    }
    
    public List<Products> findByCategoryId(Long id) {
        return repository.findAllByCategoryId(id);
    }
}
