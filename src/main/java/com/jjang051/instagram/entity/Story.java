package com.jjang051.instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Story extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String caption;
    private String imgUrl;
    private String content;

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OrderBy("regDate asc")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "story")
    private List<Like> likes;
}
