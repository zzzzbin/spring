package com.example.demo.application.service;

import com.example.demo.domain.user.model.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class UserApplicationService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * File save destination
     */
    private String filePath = "/Users/trung/";
    /**
     * Path delimiter
     */
    private static final String SEPARATOR = File.separator;

    public Map<String, Integer> getGenderMap(Locale locale) {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        String male = messageSource.getMessage("male", null, locale);
        String female = messageSource.getMessage("female", null, locale);
        genderMap.put(male, 1);
        genderMap.put(female, 2);
        return genderMap;
    }

    /**
     * Save user list to CSV
     */
    public void saveUserCsv(List<MUser> userList, String fileName) throws IOException { // CSV string creation
        StringBuilder sb = new StringBuilder();
        for (MUser user : userList) {
            sb.append(user.toCsv());
        }
        // Create file save destination path
        Path path = Paths.get(filePath + SEPARATOR + fileName); // byte array creation
        byte[] bytes = sb.toString().getBytes();
        // File writing
        Files.write(path, bytes);
    }

    /**
     * Get CSV file.
     */
    public byte[] getCsv(String fileName) throws IOException { // Path
        String path = "file:" + filePath + SEPARATOR + fileName;

        //file: Gets the file on the file system.
        //classpath: Get the file on the classpath.
        //s3: Get the file from Amazon's S3 storage service.

        // Get file
        Resource resource = resourceLoader.getResource(path);
        File file = resource.getFile();
        // Get byte array
        return Files.readAllBytes(file.toPath());
    }
}
