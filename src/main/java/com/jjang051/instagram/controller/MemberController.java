package com.jjang051.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private static final String PREFIX = "member/";
    @GetMapping("/login")
    public String login() {
        return PREFIX+"/login";
    }
}
