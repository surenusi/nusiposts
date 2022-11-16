package com.surenusi.springbootposts.dto.posts;

import com.surenusi.springbootposts.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostsMainResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private int viewCount;
    private String modifiedDate;

    public PostsMainResponseDto(Posts posts) {
        id = posts.getId();
        title = posts.getTitle();
        content = posts.getContent();
        author = posts.getAuthor();
        viewCount = posts.getViewCount();
        modifiedDate = toStringDateTime(posts.getModifiedDate());
    }

    //LocalDateTime타입 -> String타입 형변환
    public String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(dateTimeFormatter::format)
                .orElse("");
    }
}
