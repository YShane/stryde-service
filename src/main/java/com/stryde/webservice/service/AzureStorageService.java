package com.stryde.webservice.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;

public interface AzureStorageService {

    void uploadImageAsProfilePicture(UserDto userDto, File thumbnailFile, MultipartFile originalFile) throws FileUploadErrorException;
}
