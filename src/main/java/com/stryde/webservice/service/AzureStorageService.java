package com.stryde.webservice.service;
;
import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;

public interface AzureStorageService {

    void uploadImageAsProfilePicture(UserDto userDto, File thumbnailFile, MultipartFile originalFile) throws FileUploadErrorException;
}
