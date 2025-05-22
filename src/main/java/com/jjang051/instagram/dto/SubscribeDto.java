package com.jjang051.instagram.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeDto {
    private String userID;
    private String userName;
    private String userEmail;
    private boolean isMe;        // 본인인지
    private boolean isSubscribed; // 내가 구독하고 있는지
}
