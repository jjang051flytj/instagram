package com.jjang051.instagram.config;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    //application시작할때 동작한다.
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String admidID = "admin";
        Optional<Member> optionalMember = memberDao.findByUserID(admidID);
        if(!optionalMember.isPresent()) {
            Member adminMember = Member.builder()
                    .userID(admidID)
                    .role(Role.ROLE_ADMIN)
                    .userName("관리자")
                    .userEmail("jjang051@hanmail.net")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .build();
            memberDao.save(adminMember);
        } else {
            System.out.println("관리자 계정이 이미 있습니다.");
        }
        Optional<Member> hongMember = memberDao.findByUserID("hong");
        if(!hongMember.isPresent()) {
            Member member1 = Member.builder()
                    .userID("hong")
                    .userName("홍길동")
                    .userEmail("hong@naver.com")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_USER)
                    .build();
            memberDao.save(member1);
        }
        Optional<Member> jjang051Member = memberDao.findByUserID("jjang051");
        if(!jjang051Member.isPresent()) {
            Member member2 = Member.builder()
                    .userID("jjang051")
                    .userName("장성호")
                    .userEmail("jjang051@naver.com")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_USER)
                    .build();
            memberDao.save(member2);
        }
        Optional<Member> jjang052Member = memberDao.findByUserID("jjang052");
        if(!jjang052Member.isPresent()) {
            Member member2 = Member.builder()
                    .userID("jjang052")
                    .userName("유재석")
                    .userEmail("jjang052@naver.com")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_USER)
                    .build();
            memberDao.save(member2);
        }
        Optional<Member> jjang053Member = memberDao.findByUserID("jjang053");
        if(!jjang053Member.isPresent()) {
            Member member2 = Member.builder()
                    .userID("jjang053")
                    .userName("정형돈")
                    .userEmail("jjang053@naver.com")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_USER)
                    .build();
            memberDao.save(member2);
        }

    }
}
