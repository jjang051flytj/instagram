package com.jjang051.instagram.api;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.LikeDto;
import com.jjang051.instagram.entity.Like;
import com.jjang051.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;
    @PostMapping("/story/{id}/like")
    public void like(@PathVariable int id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("like id {}", id);
        /*
        likeService.findById(id);
        Like like = Like.builder()
                            .story(id)
                            .userID(customUserDetails.getLoggedMember().getUserID())
                            .build();
         */
        likeService.like(id,customUserDetails.getLoggedMember().getUserID());
    }
}
