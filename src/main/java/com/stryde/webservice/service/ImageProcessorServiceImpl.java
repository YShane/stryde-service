package com.stryde.webservice.service;

import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.exception.FileUploadErrorException;
import com.stryde.webservice.utils.FileUtils;
import net.coobird.thumbnailator.Thumbnailator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ImageProcessorServiceImpl implements ImageProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageProcessorServiceImpl.class);

    private final int THUMBNAIL_HEIGHT_MOBILE = 36;
    private final int THUMBNAIL_WIDTH_MOBILE =  36;


    public File generateThumbnail(MultipartFile multipartFile) throws FileUploadErrorException {

        String fileName= multipartFile.getOriginalFilename();
        File outFile = new File(fileName);
        LOGGER.debug("filename: {} " + fileName);

        try {

        File file = FileUtils.convertMultipartFileToFile(multipartFile);
            Thumbnailator.createThumbnail(file, outFile, THUMBNAIL_HEIGHT_MOBILE, THUMBNAIL_WIDTH_MOBILE);
        } catch (IOException e){
            throw new FileUploadErrorException(AppErrorCode.UPLOAD_FILE_FAILURE);
        }

        return outFile;
    }
}
