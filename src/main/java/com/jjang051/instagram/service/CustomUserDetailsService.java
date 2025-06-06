package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        log.info("userID==={}",userID);
        Optional<Member> optionalMember = memberDao.findByUserID(userID);
        if(optionalMember.isPresent()) {
            return new CustomUserDetails(optionalMember.get());
        }
        throw new UsernameNotFoundException("아이디 패스워드 확인해 주세요");
    }
}
