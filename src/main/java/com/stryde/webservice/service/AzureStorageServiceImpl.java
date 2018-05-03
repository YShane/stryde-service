package com.stryde.webservice.service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.exception.FileUploadErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class AzureStorageServiceImpl implements AzureStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AzureStorageServiceImpl.class);

    private final String containerName = "";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CloudStorageAccount cloudStorageAccount;

    private final String ORIGINAL_PROFILE_PICTURE = "_profile_picture";
    private final String ORIGINAL_PROFILE_PICTURE_THUMBNAIL = "_profile_picture_thumbnail";

    @Override
    public void uploadImageAsProfilePicture(UserDto userDto, File thumbnailFile, MultipartFile originalFile) throws FileUploadErrorException {

        final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();

        try {
            // Get a reference to a container.
            // The container name must be lower case
            final CloudBlobContainer container = blobClient.getContainerReference(containerName);

            // Create the container if it does not exist.
            container.createIfNotExists();
            // Create a permissions object.
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
            // Include public access in the permissions object.
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            // Set the permissions on the container.
            container.uploadPermissions(containerPermissions);

            //photo names are a combination of the user's emailaddress and a string
            String originalFileName = userDto.getEmail() + ORIGINAL_PROFILE_PICTURE;
            String thumbnailFileName = userDto.getEmail() + ORIGINAL_PROFILE_PICTURE_THUMBNAIL;


        CloudBlockBlob originalBlob = container.getBlockBlobReference(originalFileName);
        CloudBlockBlob thumbnailBlob = container.getBlockBlobReference(thumbnailFileName);

        //upload original
        InputStream orginalIs = originalFile.getInputStream();
        originalBlob.upload(orginalIs, originalFile.getSize());
        orginalIs.close();

        FileInputStream fis = null;

        //upload original
        fis = new FileInputStream(thumbnailFile);
        thumbnailBlob.upload(fis, thumbnailFile.length());
        fis.close();

        } catch (StorageException | URISyntaxException  | IOException  e) {
            LOGGER.error("Getting container reference Issue {} " + e);
            throw new FileUploadErrorException(AppErrorCode.UPLOAD_FILE_FAILURE);
        }

    }
}