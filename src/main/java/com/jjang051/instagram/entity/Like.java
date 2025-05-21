package com.jjang051.instagram.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name="insta_like")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storyID")
    private Story story;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userID",referencedColumnName = "userID")
    private Member member;





}
