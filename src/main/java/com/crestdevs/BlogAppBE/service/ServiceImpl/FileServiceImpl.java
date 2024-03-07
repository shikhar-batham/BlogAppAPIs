package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.service.FileService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final List<String> ALLOWED_IMAGE_FORMATS = Arrays.asList("png", "jpeg", "jpg");

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        assert name != null;

        String fileExtension = getFileExtension(name);

        if (!isAllowedImageFormat(fileExtension)) {
            return null;
        }

        if (file.getSize() > 2 * 1024) {

            File compressedImageFile = compressImage(file, fileExtension);
            FileInputStream input = new FileInputStream(compressedImageFile);
            file = new MockMultipartFile("file", file.getName(), "text/plain", new BufferedInputStream(input));
        }

        File fileObj = convertMultiPartFileToFile(file);

        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + "/" + fileName;

        return fileName;
    }

    @Override
    public byte[] getResource(String path, String fileName) throws IOException {

        String fullPath = path + "/" + fileName;

        return null;
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

    private File convertMultiPartFileToFile(MultipartFile file) {

        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {

            fos.write(file.getBytes());

        } catch (IOException e) {
            log.error("Error converting multipart file to file" + e);
        }
        return convertedFile;
    }
}
