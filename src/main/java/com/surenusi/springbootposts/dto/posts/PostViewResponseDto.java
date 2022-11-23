package com.surenusi.springbootposts.dto.posts;

import com.surenusi.springbootposts.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostViewResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private int viewCount;
    private String createdDate;
    private String modifidedData;

    public PostViewResponseDto(Posts posts) {
        id = posts.getId();
        title = posts.getTitle();
        author = posts.getAuthor();
        content = posts.getContent();
        viewCount = posts.getViewCount();
        createdDate = toStringDateTime(posts.getCreatedDate());
        modifidedData = toStringDateTime(posts.getModifiedDate());
    }

    //LocalDateTime타입 -> String타입 형변환
    public String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(dateTimeFormatter::format)
                .orElse("");
    }
}
