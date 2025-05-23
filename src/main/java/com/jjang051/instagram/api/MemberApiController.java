package com.jjang051.instagram.api;


import com.jjang051.instagram.dto.MemberDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/member/{loggedMemberID}/profile")
    public Map<String,Object> hongProfile(
            @PathVariable String loggedMemberID,
            @RequestParam MultipartFile profileImg) {
        Member member = memberService.chageProfile(loggedMemberID,profileImg);
        String profileImgPath = member.getProfileImg();
        Map<String,Object> resultMap = new HashMap<>();
        if(member != null) {
            resultMap.put("isProfileChange",true);
            resultMap.put("profileImgPath",profileImgPath);
        } else {
            resultMap.put("isProfileChange",false);
            resultMap.put("profileImgPath",null);
        }

        return resultMap;
    }
}
