package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
