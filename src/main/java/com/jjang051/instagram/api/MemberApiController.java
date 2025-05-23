package com.jjang051.instagram.api;


import com.jjang051.instagram.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class MemberApiController {
    @PostMapping("/member/hong/profile")
    public Map<String,Object> hongProfile(
            @ModelAttribute MemberDto memberDto) {
        log.info("userID==={},", memberDto.toString());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }
}
