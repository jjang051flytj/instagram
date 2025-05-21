package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.LikeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeDao likeDao;

    @Transactional
    public int like(int id, String userID) {
        return likeDao.like(id, userID);
    }
    @Transactional
    public int hate(int id, String userID) {
        return likeDao.hate(id, userID);
    }
}
