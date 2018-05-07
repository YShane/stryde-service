package com.stryde.webservice.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.exception.FileUploadErrorException;

public interface ImageProcessorService {

    File generateThumbnail(MultipartFile file) throws FileUploadErrorException;
}
