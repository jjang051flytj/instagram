package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.service.StorySevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        //service == business logic
        Story story = storySevice.write(storyDto);
        return "redirect:/story/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
       List<StoryUploadDto> storyUploadDtoList = storySevice.findAll();
       model.addAttribute("storyUploadDtoList", storyUploadDtoList);
       return "story/list";
    }
}
