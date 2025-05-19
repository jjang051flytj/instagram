package com.jjang051.instagram.dao;

import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//db관련된 annotation
@Repository
@RequiredArgsConstructor
public class StoryDao {
    private final StoryRepository storyRepository;

    public Story save(Story saveStory) {
        return storyRepository.save(saveStory); //
    }

    public List<StoryUploadDto> findAll() {
        List<Story> storyList = storyRepository.findAll();
        List<StoryUploadDto> storyDtoList = storyList.stream().map(
                story -> StoryUploadDto.builder()
                        .id(story.getId())
                        .caption(story.getCaption())
                        .content(story.getContent())
                        .imgUrl(story.getImgUrl())
                        .regDate(story.getRegDate())
                        .modifyDate(story.getModifyDate())
                        .build()
        ).toList();
        return storyDtoList;
    }
}
