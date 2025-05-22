package com.jjang051.instagram.service;

import com.jjang051.instagram.dao.SubscribeDao;
import com.jjang051.instagram.dto.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeDao subscribeDao;

    @Transactional
    public int subscribe(String fromMemberID, String toMemberID) {
        return subscribeDao.subscribe(fromMemberID, toMemberID);
    }


    public List<SubscribeDto> getSubscribeList(String currentUserID, String targetUserID) {
        return subscribeDao.getSubscribeList(currentUserID, targetUserID);
    }
}
