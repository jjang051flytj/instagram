package com.jjang051.instagram.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UUIDFileRenameStrategy implements FileRenameStrategy {

    @Override
    public String renameFile(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return fileName +"_"+uuid+"."+extension;
    }
}
