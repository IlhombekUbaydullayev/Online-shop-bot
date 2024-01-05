package shop.uz.onlineshopbot.entities;

import jakarta.persistence.*;
import lombok.*;
import shop.uz.onlineshopbot.enums.UserState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    @Enumerated(EnumType.STRING)
    private UserState state = UserState.START;
    private String name;
    @OneToMany( mappedBy = "users")
    private List<Address> address;
    private String tx = "";
    private boolean isCheckeds = false;
    private int isChecked;
}
