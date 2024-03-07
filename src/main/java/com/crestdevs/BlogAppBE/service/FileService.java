package com.crestdevs.BlogAppBE.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadImage(String path, MultipartFile multipartFile) throws IOException;

    byte[] getResource(String path, String fileName) throws IOException;
}
