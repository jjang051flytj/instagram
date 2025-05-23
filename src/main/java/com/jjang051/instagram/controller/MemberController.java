package com.jjang051.instagram.controller;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.InfoDto;
import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.dto.SubscribeDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Subscribe;
import com.jjang051.instagram.repository.MemberRepository;
import com.jjang051.instagram.service.MemberService;
import com.jjang051.instagram.service.SubscribeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SubscribeService subscribeService;
    private final MemberRepository memberRepository;

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
    public String info(@PathVariable("userID") String userID, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        InfoDto myInfo = memberService.findByUserID(customUserDetails.getLoggedMember().getUserID(),userID);
        //List<SubscribeDto> subscribeList = subscribeService.getSubscribeList(customUserDetails.getLoggedMember().getUserID(), userID);
        model.addAttribute("myInfo",myInfo);
        //model.addAttribute("subscribeList",subscribeList);
        //log.info("myInfo:{}",myInfo.getStoryList());
        return "member/info";
    }

    @GetMapping("/{userID}/subscribers")
    public String showSubscribers(@PathVariable String userID, Model model) {
        Member member = memberRepository.findByUserID(userID)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 구독자 목록 추출 (Subscribe 객체 → fromMember)
        List<Member> subscriberList = member.getSubscribesToMe().stream()
                .map(Subscribe::getFromMember)
                .toList();

        model.addAttribute("subscriberList", subscriberList);
        model.addAttribute("member", member);
        log.info("subscriberList:{}",subscriberList.size());
        return "member/subscribers"; // 구독자 보여주는 Thymeleaf 템플릿
    }


}
