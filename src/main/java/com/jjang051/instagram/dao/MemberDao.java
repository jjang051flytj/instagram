package com.jjang051.instagram.dao;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class MemberDao {
    private final MemberRepository memberRepository; // jpa
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findByUserID(String userID) {
        log.info("userID==={}",userID);
        Optional<Member> optionalMember =  memberRepository.findByUserID(userID);
        return optionalMember;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public int deleteByUserID(String userID) {
        return memberRepository.deleteByUserID(userID);
    }
}
