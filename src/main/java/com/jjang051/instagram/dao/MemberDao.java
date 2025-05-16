package com.jjang051.instagram.dao;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberDao {
    private final MemberRepository memberRepository; // jpa
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
