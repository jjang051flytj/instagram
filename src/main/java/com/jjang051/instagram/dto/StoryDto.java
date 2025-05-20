package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Story;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryDto {
    private int id;
    private MultipartFile file;
    private String caption;
    private String imgUrl;
    private String content;
    private List<CommentDto> commentList;
    private LocalDateTime modifyDate;
    private LocalDateTime regDate;
    private String strRegDate;

    public static Story toStory(StoryDto storyDto) {
        return Story.builder()
                .imgUrl(storyDto.imgUrl)
                .caption(storyDto.caption)
                .content(storyDto.content)
                .build();
    }

    public Story toStory() {
        return Story.builder()
                .imgUrl(this.imgUrl)
                .caption(this.caption)
                .content(this.content)
                .build();
    }
}
