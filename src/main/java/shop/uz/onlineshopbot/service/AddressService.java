package shop.uz.onlineshopbot.service;

import org.springframework.stereotype.Service;
import shop.uz.onlineshopbot.entities.Address;
import shop.uz.onlineshopbot.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address create(Address address) {
        Address save = repository.save(address);
        return save;
    }

    public Address update(Long id,Address address) {
        Optional<Address> byId = repository.findById(id);
        if (byId.isPresent()) {
            Address address2 = byId.get();
            address2.setLatitude(address.getLatitude());
            address2.setLongitude(address.getLongitude());
            repository.save(address2);
            return address2;
        }
        return new Address();
    }

    public List<Address> findAllByUserId(Long userId) {
        return repository.findAllByUsersId(userId);
    }

    public Boolean findByLatLong(Long userId,Double latitude,Double longitude) {
        Optional<Address> location = repository.findByUsersIdAndLatitudeAndLongitude(userId,latitude, longitude);
        return location.isPresent();
    }
}
