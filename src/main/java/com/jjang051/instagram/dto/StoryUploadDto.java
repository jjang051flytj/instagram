package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Story;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoryUploadDto {
    private MultipartFile file;
    private String caption;
    private String imgUrl;

    public Story toStory() {
        return Story.builder()
                .imgUrl(this.imgUrl)
                .caption(this.caption)
                .build();
    }
}
