package com.jjang051.instagram.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private int id;
    private String userID;
    private int storyID;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;
}
