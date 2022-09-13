package com.temchik.lms.service.file;

import com.temchik.lms.common.constants.FileType;

import java.io.File;
import java.io.InputStream;

public interface FileService {

    String upload(FileType type, File file);

    <T extends InputStream> T load(String fileName);
}
