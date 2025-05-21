package com.jjang051.instagram.api;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.LikeDto;
import com.jjang051.instagram.entity.Like;
import com.jjang051.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;
    @PostMapping("/story/{id}/like")
    public Map<String,Object> like(@PathVariable int id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("like id {}", id);
        /*
        likeService.findById(id);
        Like like = Like.builder()
                            .story(id)
                            .userID(customUserDetails.getLoggedMember().getUserID())
                            .build();
         */
        int result = likeService.like(id,customUserDetails.getLoggedMember().getUserID());
        Map<String,Object> resultMap = new HashMap<>();
        if(result>0) {
            resultMap.put("isSuccess",true);
        } else {
            resultMap.put("isSuccess",false);
        }
        return resultMap;
    }

    @DeleteMapping("/story/{id}/hate")
    public Map<String,Object>  hate(@PathVariable int id,
                    @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("like id {}", id);
        int result = likeService.hate(id,customUserDetails.getLoggedMember().getUserID());
        Map<String,Object> resultMap = new HashMap<>();
        if(result>0) {
            resultMap.put("isSuccess",true);
        } else {
            resultMap.put("isSuccess",false);
        }
        return resultMap;
    }
}
