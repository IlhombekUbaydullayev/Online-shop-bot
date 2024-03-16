package shop.uz.onlineshopbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.uz.onlineshopbot.service.OrderBaseService;

@RestController
@RequestMapping("/api/orderBase")
public class OrderBaseController {
    private final OrderBaseService service;

    public OrderBaseController(OrderBaseService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
