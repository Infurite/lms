package com.temchik.lms.controller;

import com.temchik.lms.common.constants.FileType;
import com.temchik.lms.common.util.FileUtils;
import com.temchik.lms.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/image")
    public String uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) {
        return fileService.upload(FileType.IMAGE, FileUtils.convertMultiPartFileToFile(multipartFile));
    }
}
