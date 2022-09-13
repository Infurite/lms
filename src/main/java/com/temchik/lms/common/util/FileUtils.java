package com.temchik.lms.common.util;

import com.temchik.lms.common.constants.FileType;
import com.temchik.lms.common.exception.BusinessException;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static File convertMultiPartFileToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            LOG.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
        }
        return file;
    }

    public static void validateFile(FileType type, File file) {
        if (!type.getExtensions().contains(Files.getFileExtension(file.getName()).toLowerCase())) {
            throw new BusinessException("FileType is not supported");
        }
    }
}
