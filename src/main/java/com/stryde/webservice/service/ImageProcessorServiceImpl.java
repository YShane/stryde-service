package com.stryde.webservice.service;

import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.exception.FileUploadErrorException;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ImageProcessorServiceImpl implements ImageProcessorService {

    private final int THUMBNAIL_HEIGHT_MOBILE = 36;
    private final int THUMBNAIL_WIDTH_MOBILE =  36;

    public OutputStream generateThumbnail(MultipartFile file) throws FileUploadErrorException {

        String fileName= file.getOriginalFilename();

        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnailator.createThumbnail(file.getInputStream(), outputStream, THUMBNAIL_HEIGHT_MOBILE, THUMBNAIL_WIDTH_MOBILE);
        } catch (IOException e){
            throw new FileUploadErrorException(AppErrorCode.UPLOAD_FILE_FAILURE);
        }

        return outputStream;
    }
}
