package com.jjang051.instagram.dao;

import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public StoryUploadDto findByDtoId(int id) {
        Optional<Story> findedStory = storyRepository.findById(id);
        if(findedStory.isPresent()) {
            //dto로변환해서 리턴해준다.Story story = findedStory.get();
            Story story = findedStory.get();

            return StoryUploadDto.builder()
                                .id(story.getId())
                                .caption(story.getCaption())
                                .content(story.getContent())
                                .imgUrl(story.getImgUrl())
                                .regDate(story.getRegDate())
                                .modifyDate(story.getModifyDate())
                            .build();
        }
        throw new IllegalArgumentException("찾을 수 없는 스토리입니다.");
    }

    public Story findById(int id) {
        return storyRepository.findById(id).orElse(null);
    }
}
