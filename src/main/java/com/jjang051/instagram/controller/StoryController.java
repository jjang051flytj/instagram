package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.StoryDto;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.service.StorySevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String write(@ModelAttribute StoryDto storyDto) {
        log.info("storyDto: {}", storyDto);
        //service == business logic
        Story story = storySevice.write(storyDto);
        return "redirect:/story/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
       List<StoryDto> storyUploadDtoList = storySevice.findAll();
       model.addAttribute("storyUploadDtoList", storyUploadDtoList);
       return "story/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        StoryDto detail = storySevice.findByDtoId(id);
        model.addAttribute("detail", detail);
        return "story/detail";
    }
    @PostMapping("/ck-upload")
    @ResponseBody
    public Map<String, String> ckUpload(@RequestParam MultipartFile upload) {
        //<input type="file" name="upload">
        //MultipartFile serverUpload = upload;
        log.info("upload: {}", upload.getOriginalFilename());
        String uploadedFile = storySevice.uploadImg(upload);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", "/upload/"+uploadedFile);
        return resultMap;
    }
}
