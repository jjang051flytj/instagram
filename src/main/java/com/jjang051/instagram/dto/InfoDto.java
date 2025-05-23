package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoDto {
    private boolean pageOwner;
    private Member member;
    private int subscribeCount;
    private boolean subscribeStatus;
    private int storyTotal;
}
