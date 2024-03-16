package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findAllByUsersId(Long users_id);
    Optional<Address> findByUsersIdAndLatitudeAndLongitude(Long users_id, Double latitude, Double longitude);
    Optional<Address> findByUsersIdAndLatitude(Long users_id,Double latitude);
}
