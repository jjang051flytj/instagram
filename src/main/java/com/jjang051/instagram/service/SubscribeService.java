package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.SubscribeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeDao subscribeDao;

    @Transactional
    public int subscribe(String fromMemberID, String toMemberID) {
        return subscribeDao.subscribe(fromMemberID, toMemberID);
    }
}
