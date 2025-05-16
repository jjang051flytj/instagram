package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<MemberDto> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "admin/member/list";
    }

    @DeleteMapping("/member/delete/{userID}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable String userID) {
        int result = memberService.deleteByUserID(userID);
        Map<String,Object> resultMap = new HashMap<>();
        if(result > 0) {
            resultMap.put("isDelete", true);
        }else {
            resultMap.put("isDelete", false);
        }
        return resultMap;
    }
}
