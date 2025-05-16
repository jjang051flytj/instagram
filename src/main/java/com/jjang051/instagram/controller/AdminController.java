package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;


    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "admin/member/list";
    }
}
