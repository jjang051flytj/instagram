package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.service.StorySevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/story")
public class StoryController {

    private final StorySevice storySevice;

    @GetMapping("/write")
    public String write() {
        return "story/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute StoryUploadDto storyDto) {
        log.info("storyDto: {}", storyDto);
        //파일 받아서 upload 구현  service
        storySevice.write(storyDto);
        return "story/write";
    }

}
