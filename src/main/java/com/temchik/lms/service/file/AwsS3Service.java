package com.temchik.lms.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.temchik.lms.common.constants.FileType;
import com.temchik.lms.common.util.FileUtils;
import com.temchik.lms.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AwsS3Service implements FileService {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.images.bucket-name}")
    private String imageBucket;

    @Value("${aws.s3.files.bucket-name}")
    private String fileBucket;

    @Override
    public String upload(FileType type, File file) {
        FileUtils.validateFile(type, file);

        final String bucketName = getBucket(type);
        final String fileName = generateFileName(type, file.getName());

        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
        file.delete();

        return fileName;
    }

    @Override
    public <T extends InputStream> T load(String fileName) {
        throw new UnsupportedOperationException("This method is not supported yet!");
    }

    private String generateFileName(FileType type, String fileName) {
        return type.name().toLowerCase()
                .concat(StringUtils.generateRandomString(50).concat("_").concat(fileName));
    }

    private String getBucket(FileType type) {
        switch (type) {
            case DOCUMENT:
                return fileBucket;
            case IMAGE:
                return imageBucket;
            default:
                throw new UnsupportedOperationException("FileType is not supported yet!");
        }
    }
}
