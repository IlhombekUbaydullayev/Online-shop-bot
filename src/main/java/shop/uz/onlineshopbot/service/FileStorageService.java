package shop.uz.onlineshopbot.service;

import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.uz.onlineshopbot.entities.FileStorage;
import shop.uz.onlineshopbot.enums.FileStorageStatus;
import shop.uz.onlineshopbot.repositories.FileStorageRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class FileStorageService {
    private final FileStorageRepository repository;

    private final Hashids hashids;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageService(FileStorageRepository repository) {
        this.repository = repository;
        this.hashids = new Hashids(getClass().getName(),4);
    }

    public String save(MultipartFile multipartFile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        repository.save(fileStorage);

        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d",this.uploadFolder,
                1900 + now.getYear(), 1 + now.getMonth(), now.getDate()));

        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            log.info("file created!");
        }
        String encode = hashids.encode(fileStorage.getId());
        fileStorage.setHashId(encode);
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",1900 + now.getYear(),
                1 + now.getMonth(),now.getDate(),fileStorage.getHashId(),fileStorage.getExtension()));
        FileStorage save = repository.save(fileStorage);
//        Category byId = categoryService.findById(categoryId);
//        byId.setFileStorage(save);
//        categoryService.update(categoryId,byId);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder,String.format("%s.%s",fileStorage.getHashId(),fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return encode;
    }

    @Transactional(readOnly = true)
    public FileStorage findByHashId(String hashId) {
        Optional<FileStorage> byHashId = repository.findByHashId(hashId);
        if (byHashId.isPresent()) {
            return repository.findByHashId(hashId).get();
        }
        return null;
    }

    private String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() -2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
