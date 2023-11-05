package shop.uz.onlineshopbot.entities;

import jakarta.persistence.*;
import lombok.*;
import shop.uz.onlineshopbot.enums.UserState;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    @Enumerated(EnumType.STRING)
    private UserState state = UserState.START;
    private String name;
}
