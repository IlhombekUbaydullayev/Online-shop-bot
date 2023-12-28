package shop.uz.onlineshopbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.uz.onlineshopbot.entities.Products;
import shop.uz.onlineshopbot.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/product")
    public ResponseEntity create(@RequestBody Products products, @RequestParam String hashId,@RequestParam Long categoryId) {
        service.create(products,hashId,categoryId);
        return ResponseEntity.ok("successfully created");
    }
}
