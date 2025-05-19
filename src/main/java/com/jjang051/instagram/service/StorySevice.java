package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.StoryDao;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.utils.DateTimeRenameStrategy;
import com.jjang051.instagram.utils.FileRenameStrategy;
import com.jjang051.instagram.utils.UUIDFileRenameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        //dto받아서 upload폴더에 이미지 업로드 하고
        // dao에 save()
        //이름을 바꿔서
        String originalFileName =  storyDto.getFile().getOriginalFilename();
        //UUID uuid = UUID.randomUUID();
        //String renameFileName = uuid + "_" + originalFileName;
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
        Story story = StoryUploadDto.toStory(storyDto);
        //Story story = storyDto.toStory();

        return storyDao.save(story);
    }

    public List<StoryUploadDto> findAll() {

        return storyDao.findAll();
    }
    //db에 입출력
}
