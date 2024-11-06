package com.toyproject.hello.dev.image.service;

import com.toyproject.hello.dev.image.entity.Image;
import com.toyproject.hello.dev.image.repository.ImageRepository;
import com.toyproject.hello.dev.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    ImageHandler imageHandler = new ImageHandler();

    @Autowired
    ImageRepository imageRepository;

    public boolean saveImage(int postId , MultipartFile multiImage) throws IOException, NoSuchAlgorithmException {
        
        String uniqueFileName = UUID.randomUUID().toString() + "_" + multiImage.getOriginalFilename();
        Path filePath = Paths.get(uploadPath + uniqueFileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.copy(multiImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Image imageEntity = Image.builder()
                    .postId(postId)
                    .imageUrl(uniqueFileName)
                    .build();
            imageRepository.save(imageEntity);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
