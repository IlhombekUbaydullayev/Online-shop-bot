package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.User;
import shop.uz.onlineshopbot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(Long id,User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user2 = byId.get();
            user2.setChatId(user.getChatId());
            user2.setName(user.getName());
            user2.setState(user.getState());
            user2.setAddress(user.getAddress());
            user2.setTx(user.getTx());
            user2.setCheckeds(user.isCheckeds());
            userRepository.save(user2);
            return user2;
        }
        return new User();
    }

    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }
}
