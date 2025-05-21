package com.jjang051.instagram.dao;

import com.jjang051.instagram.entity.Like;
import com.jjang051.instagram.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LikeDao {
    private final LikeRepository likeRepository;
    public int like(int id, String userID) {
        return likeRepository.like(id,userID);
    }
}
