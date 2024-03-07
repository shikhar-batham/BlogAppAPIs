package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image getImage(String fileName);

    Image saveImage(MultipartFile file) throws IOException;

    void deletePreviousImage(String imageName);
}
