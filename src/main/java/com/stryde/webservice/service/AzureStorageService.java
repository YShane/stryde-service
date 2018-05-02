package com.stryde.webservice.service;

import com.microsoft.azure.storage.StorageException;
import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.URISyntaxException;

public interface AzureStorageService {

    void uploadImageAsProfilePicture(UserDto userDto, OutputStream thumbnail, MultipartFile originalFile) throws FileUploadErrorException;
}
