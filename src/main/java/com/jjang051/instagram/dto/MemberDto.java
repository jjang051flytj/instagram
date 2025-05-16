package com.jjang051.instagram.dto;

import com.jjang051.instagram.constant.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userID;
    private String userPW;
    private String userName;
    private String userEmail;
    private Role role; //Role 상수 처리할 예정 enum

    //admin, member, manager
}
