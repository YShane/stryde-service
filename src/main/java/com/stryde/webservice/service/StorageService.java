package com.stryde.webservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;

public interface StorageService {

    void storeProfilePicture(UserDto userDto, MultipartFile file) throws FileUploadErrorException;
}
