package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Story;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String content;
    private String author;
    private int storyID;
    private LocalDateTime regDate;
}
