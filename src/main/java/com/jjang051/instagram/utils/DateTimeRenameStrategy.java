package com.jjang051.instagram.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeRenameStrategy implements FileRenameStrategy {
    @Override
    public String renameFile(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return fileName +"_"+now+"."+extension;
    }
}
