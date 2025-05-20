package com.jjang051.instagram.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgExtractor {
    public static String extract(String html) {
        if(html!=null&&!html.isEmpty()){
            Pattern pattern = Pattern.compile("<img[^>]+src=[\"']?([^\"'>]+)[\"']?");
            Matcher matcher = pattern.matcher(html);
            if(matcher.find()){
                return matcher.group(1);
            }
        }
        return null;
    }
}
