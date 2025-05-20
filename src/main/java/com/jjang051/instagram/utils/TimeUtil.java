package com.jjang051.instagram.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtil {
    public static String getRelativeTime(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, now); //날짜 계산 해줌...
        if(duration.toMinutes() < 10) {
            return "조금 전";
        } else if(duration.toMinutes() < 60) {
            return duration.toMinutes()+"분 전";
        } else if(duration.toHours() < 24) {
            return duration.toHours()+"시간 전";
        } else if(duration.toDays() == 1) {
            return "하루 전";
        } else if(duration.toDays() < 7) {
            return duration.toDays()+"일 전";
        } else {
            return localDateTime.toLocalDate().toString();
        }
    }
}
