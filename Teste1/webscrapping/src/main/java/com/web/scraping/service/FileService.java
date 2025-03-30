package com.web.scraping.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileService {

     public File createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        return dir;
    }

    public void downloadFile(String fileUrl, File destinationFile) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo baixado: " + destinationFile.getName());
        }
    }
}
