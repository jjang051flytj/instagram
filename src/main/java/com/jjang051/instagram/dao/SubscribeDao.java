package com.jjang051.instagram.dao;

import com.jjang051.instagram.dto.SubscribeDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SubscribeDao {
    private final SubscribeRepository subscribeRepository;

    public int subscribe(String fromMemberID, String toMemberID) {
        return subscribeRepository.subscribe(fromMemberID, toMemberID);
    }

    public int unSubscribe(String fromMemberID, String toMemberID) {
        return subscribeRepository.unSubscribe(fromMemberID, toMemberID);
    }
    public List<SubscribeDto> getSubscribeList(String currentUserID, String targetUserID) {
        List<Member> subscribedMembers = subscribeRepository.findSubscribedUsers(targetUserID);

        return subscribedMembers.stream().map(member -> SubscribeDto.builder()
                .userID(member.getUserID())
                .userName(member.getUserName())
                .userEmail(member.getUserEmail())
                .isMe(member.getUserID().equals(currentUserID))
                .build()
        ).toList();
    }


    public int getSubscribeState(String fromMemberID, String toMemberID) {
        return subscribeRepository.getSubscribeState(fromMemberID, toMemberID);
    }
}
