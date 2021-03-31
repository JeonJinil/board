package com.example.board.domain;

import javax.persistence.*;

@Entity // table == entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 0;
    @Column
    private String userId;
    @Column
    private String userPw;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
}
