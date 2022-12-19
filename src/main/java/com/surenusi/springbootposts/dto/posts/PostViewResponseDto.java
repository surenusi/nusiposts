package com.surenusi.springbootposts.dto.posts;

import com.surenusi.springbootposts.common.Util;
import com.surenusi.springbootposts.domain.Posts;
import lombok.Getter;

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
        createdDate = Util.toStringDateTime(posts.getCreatedDate());
        modifidedData = Util.toStringDateTime(posts.getModifiedDate());
    }
}
