package com.jjang051.instagram.controller;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "member/signup";
        }
        Member member = memberService.save(memberDto);
        if(member != null) {
            return "redirect:/member/login";
        }
        return "member/signup";
    }

    @GetMapping("/info/{userID}")
    public String info(@PathVariable("userID") String userID, Model model) {
        Member myInfo = memberService.findByUserID(userID);
        model.addAttribute("myInfo",myInfo);
        log.info("myInfo:{}",myInfo.getStoryList());
        return "member/info";
    }
}
