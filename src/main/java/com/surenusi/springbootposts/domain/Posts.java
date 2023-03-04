package com.surenusi.springbootposts.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;
    @Column(length = 10000)
    private String content;
    @Column(nullable = false, length = 20)
    private String author;
    @Column(columnDefinition = "int DEFAULT 0 NOT NULL")
    private int viewCount;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //update 비즈니스 로직
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //조회수 증가
    public void updateViewCount() {
        this.viewCount++;
    }
}
