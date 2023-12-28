package shop.uz.onlineshopbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.uz.onlineshopbot.entities.Category;
import shop.uz.onlineshopbot.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/category")
    public ResponseEntity<?> create(@RequestBody Category categories,@RequestParam String hashId) {
        Category categories1 = service.create(categories,hashId);
        return ResponseEntity.ok(categories1);
    }

    @GetMapping("/category")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
