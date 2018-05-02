package com.stryde.webservice.service;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface StorageService {

    void storeProfilePicture(UserDto userDto, MultipartFile file) throws FileUploadErrorException;
}
