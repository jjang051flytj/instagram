package com.jjang051.instagram.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_USER("일반회원",3),
    ROLE_MANAGER("관리자",2),
    ROLE_ADMIN("최고관리자",3);
    private final String label;
    private final int level;
}
