package com.jjang051.instagram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true, nullable = false)  //unique not null
    private int userID;

    @Column(nullable = false)
    private int userPW;

    private int userName;

    @Column(unique = true, nullable = false)
    private int userEmail;
}
