package com.stryde.webservice.service;
import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.FileUploadErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;


@Service
public class StorageServiceImpl implements StorageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private ImageProcessorService imageProcessorService;

    @Autowired
    private AzureStorageService azureStorageService;

    @Override
    public void storeProfilePicture(UserDto userDto, MultipartFile file) throws FileUploadErrorException {

        if(file.isEmpty() || file==null){
            throw new IllegalArgumentException("Uploaded File is all kinds of fucked up! " + file);
        }

        OutputStream thumbnailOutputStream = imageProcessorService.generateThumbnail(file);

        //azureStorageService.uploadImageAsProfilePicture(userDto, thumbnailOutputStream, file );


    }
}
