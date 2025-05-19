package com.jjang051.instagram.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlImageExtractor {
    public static String extractFirstImageUrl(String html) {
        if (html == null || html.isEmpty()) return null;

        // <img src="..."> 패턴에서 src 안의 값만 뽑아냄
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"']?([^\"'>]+)[\"']?");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1); // 첫 번째 이미지의 src
        }
        return null;
    }
}
