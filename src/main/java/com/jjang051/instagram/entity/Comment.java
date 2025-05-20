package com.jjang051.instagram.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "insta_comment")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID") //comment table에 userID 컬럼이 생긴다.
    private Member author;  //"hong"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storyID")
    private Story story;  //1

    @Builder
    public Comment(String content, Member author, Story story) {
        this.content = content;
        this.author = author;
        this.story = story;
    }
}
