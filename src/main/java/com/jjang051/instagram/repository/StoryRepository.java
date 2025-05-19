package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StoryRepository extends JpaRepository<Story,Integer> {
}
