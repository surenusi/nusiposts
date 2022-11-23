package com.surenusi.springbootposts.dto.posts;

import com.surenusi.springbootposts.common.Util;
import com.surenusi.springbootposts.domain.Posts;
import lombok.Getter;

@Getter
public class PostsMainResponseDto {

    private Long id;
    private String title;
    private String author;
    private int viewCount;
    private String createdDate;
    private String modifiedDate;

    public PostsMainResponseDto(Posts posts) {
        id = posts.getId();
        title = posts.getTitle();
        author = posts.getAuthor();
        viewCount = posts.getViewCount();
        createdDate = Util.toStringDateTime(posts.getCreatedDate());
        modifiedDate = Util.toStringDateTime(posts.getModifiedDate());
    }
}
