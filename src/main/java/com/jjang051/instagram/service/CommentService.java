package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.CommentDao;
import com.jjang051.instagram.dao.MemberDao;
import com.jjang051.instagram.dao.StoryDao;
import com.jjang051.instagram.dto.CommentDto;
import com.jjang051.instagram.dto.StoryUploadDto;
import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Story;
import com.jjang051.instagram.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
    private final StoryDao storyDao;
    private final MemberDao memberDao;


    @Transactional
    public Comment save(CommentDto commentDto) {
        /*
        StoryUploadDto storyUploadDto =
                storyDao.findByDtoId(commentDto.getStoryID());
        Story story =  storyUploadDto.toStory(); //dto를 entity로 바꿔서 저장
        //commentDto를 comment로 바꿔서
        storyDao.save(story); //toStory()에 id가 없음 //영속화가 진행된다.
        */
        //save를 하지않고 처리
        Story story02 =  storyDao.findById(commentDto.getStoryID()); //같은 entity를 보장받고 있음

        Member member =  null;
        Optional<Member> finedMember =
                memberDao.findByUserID(commentDto.getAuthor());
        if(finedMember.isPresent()) {
            member = finedMember.get();
        }
        Comment comment = Comment.builder()
                .author(member)
                .content(commentDto.getContent())
                .story(story02)
                .build();
        //entity
        Comment savedComment = commentDao.save(comment);
        CommentDto savedCommentDto = CommentDto.builder()
                .author(savedComment.getAuthor().getUserName())
                .content(savedComment.getContent())
                .strRegDate(TimeUtil.getRelativeTime(comment.getRegDate()))
                .build();
        return savedComment;
    }
    @Transactional
    public CommentDto saveCommentDto(CommentDto commentDto) {
        /*
        StoryUploadDto storyUploadDto =
                storyDao.findByDtoId(commentDto.getStoryID());
        Story story =  storyUploadDto.toStory(); //dto를 entity로 바꿔서 저장
        //commentDto를 comment로 바꿔서
        storyDao.save(story); //toStory()에 id가 없음 //영속화가 진행된다.
        */
        //save를 하지않고 처리
        Story story02 =  storyDao.findById(commentDto.getStoryID()); //같은 entity를 보장받고 있음

        Member member =  null;
        Optional<Member> finedMember =
                memberDao.findByUserID(commentDto.getAuthor());
        if(finedMember.isPresent()) {
            member = finedMember.get();
        }
        Comment comment = Comment.builder()
                .author(member)
                .content(commentDto.getContent())
                .story(story02)
                .build();
        //entity
        Comment savedComment = commentDao.save(comment);
        CommentDto savedCommentDto = CommentDto.builder()
                .id(savedComment.getId())
                .author(savedComment.getAuthor().getUserName())
                .content(savedComment.getContent())
                .strRegDate(TimeUtil.getRelativeTime(comment.getRegDate()))
                .build();
        return savedCommentDto;
    }

    public CommentDto  deleteById(int id) {
        Comment comment = commentDao.deletebyId(id);
        return CommentDto.builder()
                .author(comment.getAuthor().getUserName())
                .content(comment.getContent())
                .strRegDate(TimeUtil.getRelativeTime(comment.getRegDate()))
                .build();
    }
}
