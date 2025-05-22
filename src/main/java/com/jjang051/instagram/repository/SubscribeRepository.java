package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value = "INSERT INTO INSTA_SUBSCRIBE (id,fromMemberID, toMemberID, regDate, modifyDate) values " +
            "(SUBSCRIBE_SEQ.NEXTVAL, :fromMemberID,:toMemberID, sysdate,sysdate)", nativeQuery = true)
    int subscribe(@Param("fromMemberID") String fromMemberID,@Param("toMemberID") String toMemberID);


    @Query(value = "SELECT m.* " +
            "FROM insta_subscribe s " +
            "INNER JOIN instgram_member m ON s.tomemberid = m.userID " +
            "WHERE s.frommemberid = :fromUserID", nativeQuery = true)
    List<Member> findSubscribedUsers(@Param("fromUserID") String fromUserID);

}
