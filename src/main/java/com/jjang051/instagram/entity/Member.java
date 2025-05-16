package com.jjang051.instagram.entity;

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
    private int userID;

    @Column(nullable = false)
    private int userPW;

    private int userName;

    @Column(unique = true, nullable = false)
    private int userEmail;
}
