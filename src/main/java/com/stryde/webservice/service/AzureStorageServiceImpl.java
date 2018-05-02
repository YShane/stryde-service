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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class AzureStorageServiceImpl implements AzureStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AzureStorageServiceImpl.class);

    private final String containerName = "";

    @Autowired
    private CloudStorageAccount cloudStorageAccount;

    @Override
    public void uploadImageAsProfilePicture(UserDto userDto, OutputStream thumbnail, MultipartFile originalFile) throws FileUploadErrorException {


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


/*        EmployeeDataVo vo = daoAndDTOService.fillEmployeeDataVoByEmployeeId(employeeId);
        String filename = vo.getEmployerEmailAddress() + "/" + vo.getEmployeeEmailAddress() +
                "/" + LocalDateTime.now().toString();

        CloudBlockBlob blob = container.getBlockBlobReference(filename);
        InputStream myFile = file.getInputStream();
        blob.upload(myFile, file.getSize());
        myFile.close();*/

        } catch (StorageException | URISyntaxException e) {
            LOGGER.error("Getting container reference Issue {} " + e);
            throw new FileUploadErrorException(AppErrorCode.UPLOAD_FILE_FAILURE);
        }

    }
}