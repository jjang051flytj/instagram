package com.jjang051.instagram.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class TimeUtil {
    public static String getRelativeTime(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, now); //날짜 계산 해줌...

        log.info("duration.toMinutes()==={}",duration.toMinutes());
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
