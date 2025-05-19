package com.jjang051.instagram.dao;

import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//db관련된 annotation
@Repository
@RequiredArgsConstructor
public class StoryDao {
    private final StoryRepository storyRepository;

    public Story save(Story saveStory) {
        return storyRepository.save(saveStory); //
    }
}
