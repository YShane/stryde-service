package com.stryde.webservice.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File( multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }
}
