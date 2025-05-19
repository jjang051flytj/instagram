package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.StoryDao;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.utils.DateTimeRenameStrategy;
import com.jjang051.instagram.utils.FileRenameStrategy;
import com.jjang051.instagram.utils.HtmlImageExtractor;
import com.jjang051.instagram.utils.UUIDFileRenameStrategy;
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

@Service
@RequiredArgsConstructor
public class StorySevice {

    //application.yml에 있는 값 들고 오기....
    @Value("${file.path}")
    String upload;

    private final StoryDao storyDao;



    public Story write(StoryUploadDto storyDto) {
        String originalFileName =  storyDto.getFile().getOriginalFilename();  //만약에 대표이미지 첨부하지 않아도 되게끔
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
        if (storyDto.getImgUrl() == null || storyDto.getImgUrl().isEmpty()) {
            String extractedImgUrl = HtmlImageExtractor.extractFirstImageUrl(storyDto.getContent());

            if (extractedImgUrl != null) {
                // /upload/abc.jpg 형태로 저장되었다면 경로만 추출
                String justFileName = extractedImgUrl.substring(extractedImgUrl.lastIndexOf("/") + 1);
                storyDto.setImgUrl(justFileName);
            } else {
                storyDto.setImgUrl("default.png"); // 기본 이미지 fallback
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
