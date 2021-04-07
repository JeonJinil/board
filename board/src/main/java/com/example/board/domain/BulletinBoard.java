package com.example.board.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BulletinBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Long memberId;
    @Column
    private Date writeDate;

    public BulletinBoard(String title, String content, Long memberId, Date writeDate) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.writeDate = writeDate;
    }

    public BulletinBoard() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long member_id) {
        this.memberId = member_id;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date write_date) {
        this.writeDate = write_date;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
