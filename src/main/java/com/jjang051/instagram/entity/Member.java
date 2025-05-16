package com.jjang051.instagram.entity;

import com.jjang051.instagram.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name="instgram_member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true, nullable = false)  //unique not null
    private String userID;

    @Column(nullable = false)
    private String userPW;

    private String userName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


}
