package com.stryde.webservice.service;

import com.stryde.webservice.exception.FileUploadErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

public interface ImageProcessorService {

    OutputStream generateThumbnail(MultipartFile file) throws FileUploadErrorException;
}
