package com.jjang051.instagram.service;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    //repository 를 직접 써도 된다.....
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public Member save(MemberDto memberDto) {
        String encodedPassword =  bCryptPasswordEncoder.encode(memberDto.getUserPW());
        memberDto.setUserPW(encodedPassword);
        memberDto.setRole(Role.ROLE_USER);
        Member savedMember = memberDto.toMember();
        return memberDao.save(savedMember);
    }
    public List<MemberDto> findAll() {
        return memberDao.findAll().stream()
                //.filter(member -> !member.getRole().name().equals("ROLE_ADMIN"))
                .filter(member -> member.getRole()!=Role.ROLE_ADMIN)
                .map(member->
                        MemberDto.builder()
                                .userID(member.getUserID())
                                .userName(member.getUserName())
                                .userEmail(member.getUserEmail())
                                .role(member.getRole())
                        .build()
                ).toList();
    }

    public int deleteByUserID(String userID) {
        return memberDao.deleteByUserID(userID);
    }
}
