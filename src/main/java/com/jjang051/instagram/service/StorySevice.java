package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.StoryDao;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class StorySevice {

    //application.yml에 있는 값 들고 오기....
    @Value("${file.path}")
    String upload;

    private final StoryDao storyDao;



    public Story write(StoryUploadDto storyDto) {
        String originalFileName =  storyDto.getFile().getOriginalFilename();  //만약에 대표이미지 첨부하지 않아도 되게끔
        //첨부파일이 있으면 업로드 해라.
        if(originalFileName!=null&&!originalFileName.isEmpty()){
            FileRenameStrategy fileRenameStrategy = new UUIDFileRenameStrategy();
            String renameFileName = fileRenameStrategy.renameFile(originalFileName);
            Path path = Paths.get(upload, renameFileName);
            try {
                Files.write(path,storyDto.getFile().getBytes());
                //storyDto.getFile().transferTo(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            storyDto.setImgUrl(renameFileName);
        }
        //만약에 대표 이미지가 없으면  글쓰기에서 쓴 이미지 중 제일 첨으 이미지를 대표 이미지로 쓰겠다.
        if(storyDto.getImgUrl()==null || storyDto.getImgUrl().isEmpty()){
            String imgSrc = ImgExtractor.extract(storyDto.getContent());
            if(imgSrc!=null) {
                String extractImg = imgSrc.substring(imgSrc.lastIndexOf("/")+1);
                storyDto.setImgUrl(extractImg);
            } else {
                //대표이미지도 없고
                //글쓰기에 이미지도 올리지 않은 경우
                storyDto.setImgUrl("story-default.jpg");

            }
        }
        Story story = StoryUploadDto.toStory(storyDto);
        //Story story = storyDto.toStory();

        return storyDao.save(story);
    }

    public List<StoryUploadDto> findAll() {
        return storyDao.findAll();
    }
    public StoryUploadDto findById(int id) {
        return storyDao.findById(id);
    }

    public String uploadImg(MultipartFile uploadImg) {
        String originalFileName =  uploadImg.getOriginalFilename();
        FileRenameStrategy fileRenameStrategy = new UUIDFileRenameStrategy();
        String renameFileName = fileRenameStrategy.renameFile(originalFileName);
        Path path = Paths.get(upload, renameFileName);
        try {
            uploadImg.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return renameFileName;
    }
    //db에 입출력
}
