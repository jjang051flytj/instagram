package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    //repository 를 직접 써도 된다.....
    private final MemberDao memberDao;

    public Member save(Member member) {
        return memberDao.save(member);
    }
}
