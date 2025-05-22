package com.jjang051.instagram.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="insta_subscribe",
        uniqueConstraints = {
            @UniqueConstraint(name="subscribe_uq",columnNames = {"fromMemberID","toMemberID"})
        }
)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Subscribe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromMemberID",referencedColumnName = "userID")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toMemberID",referencedColumnName = "userID")
    private Member toMember;

}
