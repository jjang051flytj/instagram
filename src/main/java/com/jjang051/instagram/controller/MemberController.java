package com.jjang051.instagram.controller;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup() {
        MemberDto memberDto = new MemberDto();
        memberDto.setRole(Role.ROLE_ADMIN);
        log.info("memberDto label: {}", memberDto.getRole().getLabel());
        log.info("memberDto level: {}", memberDto.getRole().getLevel());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDto memberDto) {

        return "member/signup";
    }



}
