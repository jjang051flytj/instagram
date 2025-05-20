package com.jjang051.instagram.dao;

import com.jjang051.instagram.entity.Comment;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.CommentRepository;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Bean으로 등록하기 위해서 쓴다. container에 등록된다.
@Repository
@RequiredArgsConstructor
public class CommentDao {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(int storyID) {
        Optional<Comment> optionalComment =
                commentRepository.findById(storyID);
        if(optionalComment.isPresent()) {
            return optionalComment.get();
        }
        throw new IllegalArgumentException("찾을 수 없는 스토리입니다.");
    }
}
