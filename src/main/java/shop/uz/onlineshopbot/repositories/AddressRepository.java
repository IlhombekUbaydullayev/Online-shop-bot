package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findAllByUsersId(Long users_id);
}
