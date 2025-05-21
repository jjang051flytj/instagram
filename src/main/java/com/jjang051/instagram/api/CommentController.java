package com.jjang051.instagram.api;

import com.jjang051.instagram.dto.CommentDto;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//ajax처리하는곳
//@RestController달려있으면 그대로 출력한다.
//객체를 응답하면 json으로 바꿔서 내려준다.
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService; //commentService, commentDao, Repository
    @PostMapping("/comment/write")
    public Map<String,Object> addComment(@RequestBody CommentDto commentDto,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("commentDto: {}", commentDto);
        Map<String,Object> resultMap = new HashMap<>();
        commentDto.setAuthor(customUserDetails.getLoggedMember().getUserID());
        Comment returnComment = commentService.save(commentDto);  //storyID:1, content:"댓글을 씁니다",author:"hong"
        //CommentDto returnComment = commentService.saveCommentDto(commentDto);  //storyID:1, content:"댓글을 씁니다",author:"hong"
        if(returnComment != null) {
            resultMap.put("isInsert",true);
            resultMap.put("returnComment",returnComment);
        } else {
            resultMap.put("isInsert",false);
            resultMap.put("returnComment",null);
        }
        return resultMap;
    }
}
