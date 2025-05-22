package com.jjang051.instagram.api;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PostMapping("/subscribe/{toMemberID}")
    public Map<String,Object> subscribe(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable String toMemberID) {
        String fromMemberID = customUserDetails.getLoggedMember().getUserID();
        int result =  subscribeService.subscribe(fromMemberID, toMemberID);
        Map<String,Object> resultMap = new HashMap<>();
        if(result > 0) {
            resultMap.put("isSubscribe", true);
        } else {
            resultMap.put("isSubscribe", false);
        }
        return resultMap;
    }
}
