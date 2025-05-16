package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer > {
    Optional<Member> findByUserID(String userID);  //select * from member where userID = ?

    //@Modifying
    //@Transactional
    //@Query("DELETE FROM Member m where m.userID = :userID")
    int deleteByUserID(String userID);
}
