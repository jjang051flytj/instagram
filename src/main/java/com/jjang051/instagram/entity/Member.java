package com.jjang051.instagram.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jjang051.instagram.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> commentList;


    //나는 여기 테이블에 컬럼을 만들지 않겠다.
    //보통의 경우 Many쪽이 연관관계의 주인이 된다. 즉 Many쪽에 컬럼이 생긴다.
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Story> storyList;





    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Like> likes;



}
