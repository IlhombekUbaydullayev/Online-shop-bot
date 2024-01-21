package shop.uz.onlineshopbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return service.findAll();
    }
}
