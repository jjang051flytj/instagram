package com.jjang051.instagram.dao;

import com.jjang051.instagram.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubscribeDao {
    private final SubscribeRepository subscribeRepository;

    public int subscribe(String fromMemberID, String toMemberID) {
        return subscribeRepository.subscribe(fromMemberID, toMemberID);
    }
}
