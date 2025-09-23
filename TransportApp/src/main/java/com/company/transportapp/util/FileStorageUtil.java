package com.company.transportapp.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileStorageUtil {

    public static String storeFile(MultipartFile file, String targetDir) throws IOException {
        Path dirPath = Paths.get(targetDir);
        if(!Files.exists(dirPath)) Files.createDirectories(dirPath);

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = dirPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }
}
