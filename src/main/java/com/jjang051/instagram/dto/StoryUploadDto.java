package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Story;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryUploadDto {
    private MultipartFile file;
    private String caption;
    private String imgUrl;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;



    public static Story toStory(StoryUploadDto storyUploadDto) {
        return Story.builder()
                .imgUrl(storyUploadDto.imgUrl)
                .caption(storyUploadDto.caption)
                .content(storyUploadDto.content)
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
