package com.jjang051.instagram.dto;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.entity.Member;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @NotBlank(message="아이디는 필수입력사항입니다.")
    private String userID;

    @NotBlank(message="패스워드는 필수입력사항입니다.")
    private String userPW;

    private String userName;

    @Email(message="이메일형식에 맞게 써주세요")
    @NotBlank(message="이메일은 필수입력사항입니다.")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Role role; //Role 상수 처리할 예정 enum

    private LocalDateTime regDate;
    private LocalDateTime modifyDate;

    public Member toMember() {
        return Member.builder()
                .userID(this.userID)
                .userPW(this.userPW)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .role(this.role)
                .build();
    }

}
