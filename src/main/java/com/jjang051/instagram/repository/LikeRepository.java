package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface LikeRepository extends JpaRepository<Like, Integer> {


    @Modifying
    @Query(value = "INSERT INTO insta_like(id,storyID,userID,regDate,modifyDate) values " +
            "(INSTA_LIKE_SEQ.NEXTVAL,:storyID,:userID,sysdate,sysdate)",
            nativeQuery = true)
    int like(@Param("storyID") int id, @Param("userID") String userID);

    @Modifying
    @Query(value = "DELETE FROM insta_like WHERE storyID =:storyID AND userID = :userID",
            nativeQuery = true)
    int hate(@Param("storyID") int id, @Param("userID") String userID);


}
