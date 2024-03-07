package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.entity.Image;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.repository.ImageRepository;
import com.crestdevs.BlogAppBE.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private static final List<String> ALLOWED_IMAGE_FORMATS = Arrays.asList("png", "jpeg", "jpg");

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Image getImage(String fileName) {

        return imageRepository.findByFileName(fileName).orElseThrow(() -> new ResourceNotFoundException("Image", fileName, 0));
    }

    @Override
    public Image saveImage(MultipartFile file) throws IOException {

        if (Boolean.TRUE.equals(imageRepository.existsByFileName(file.getOriginalFilename()))) {
            log.info("image {} already exists", file.getOriginalFilename());
            return Image.builder()
                    .fileName(file.getOriginalFilename())
                    .build();
        }

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        assert name != null;

        String fileExtension = getFileExtension(name);

        if (!isAllowedImageFormat(fileExtension)) {
            return null;
        }

        if (file.getSize() > (16 * 1024)) {

            File compressedImageFile = compressImage(file, fileExtension);
            FileInputStream input = new FileInputStream(compressedImageFile);
            file = new MockMultipartFile("file", file.getName(), "text/plain", new BufferedInputStream(input));
        }

        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));


        try {
            Image image = Image.builder()
                    .fileName(fileName)
                    .mimeType(file.getContentType())
                    .data(file.getBytes())
                    .build();
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePreviousImage(String imageName) {

        if (imageName.equals("default.png"))
            return;

        Image fetchedImage = this.imageRepository.findByFileName(imageName).orElseThrow(() -> new ResourceNotFoundException("Image", "provider_image" + imageName, 0));

        if (fetchedImage != null) {
            this.imageRepository.delete(fetchedImage);
        }
    }

    private String getFileExtension(String fileName) {

        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }

        return "";
    }

    private boolean isAllowedImageFormat(String fileExtension) {

        return ALLOWED_IMAGE_FORMATS.contains(fileExtension);
    }

    private File compressImage(MultipartFile file, String fileExtension) throws IOException {

        File compressedImageFile = File.createTempFile("comp", file.getOriginalFilename());

        Thumbnails.of(file.getInputStream())
                .size(800, 600) // Set the desired dimensions for the compressed image
                .outputFormat(fileExtension) // Set the desired output format
                .outputQuality(0.8) // Set the desired output quality (0.0 - 1.0)
                .toFile(compressedImageFile);

        return compressedImageFile;
    }
}
