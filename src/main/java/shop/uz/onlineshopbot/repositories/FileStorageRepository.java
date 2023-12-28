package shop.uz.onlineshopbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.uz.onlineshopbot.entities.FileStorage;

import java.util.Optional;

public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
    Optional<FileStorage> findByHashId(String hashId);
}
