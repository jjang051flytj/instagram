package com.jjang051.instagram.dao;

import com.jjang051.instagram.dto.CommentDto;
import com.jjang051.instagram.dto.StoryDto;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.repository.StoryRepository;
import com.jjang051.instagram.utils.TimeUtil;
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

    //Story안에 있는 list
    public List<StoryDto> findAll() {
        List<Story> storyList = storyRepository.findAll();
        List<StoryDto> storyDtoList = storyList.stream().map(
                story -> StoryDto.builder()
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

    public StoryDto findByDtoId(int id) {
        Optional<Story> findedStory = storyRepository.findById(id);
        if(findedStory.isPresent()) {
            //dto로변환해서 리턴해준다.Story story = findedStory.get();
            Story story = findedStory.get();
            int likeCount =  story.getLikes().size();
            List<CommentDto> commentDtoList =
                    story.getCommentList().stream()
                            .map(comment->
                                    CommentDto.builder()
                                            .id(comment.getId())
                                            .storyID(comment.getId())
                                            .content(comment.getContent())
                                            .author(comment.getAuthor().getUserName())
                                            .authorID(comment.getAuthor().getUserID())
                                            .regDate(comment.getRegDate())
                                            .strRegDate(TimeUtil.getRelativeTime(comment.getRegDate()))
                                            .build()
                                    ).toList();
            return StoryDto.builder()
                                .id(story.getId())
                                .member(story.getMember())
                                .caption(story.getCaption())
                                .content(story.getContent())
                                .imgUrl(story.getImgUrl())
                                .commentList(commentDtoList)
                                .regDate(story.getRegDate())
                                .likeCount(likeCount)
                                .modifyDate(story.getModifyDate())

                            .build();
        }
        throw new IllegalArgumentException("찾을 수 없는 스토리입니다.");
    }

    public Story findById(int id) {
        return storyRepository.findById(id).orElse(null);
    }
}
