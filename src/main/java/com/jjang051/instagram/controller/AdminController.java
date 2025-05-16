package com.jjang051.instagram.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/member/list")
    public String list() {
        return "admin/member/list";
    }
}
