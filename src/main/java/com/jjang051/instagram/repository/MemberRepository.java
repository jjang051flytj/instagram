package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer > {
}
