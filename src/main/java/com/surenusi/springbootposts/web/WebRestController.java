package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsService postsService;

    @PostMapping("/createPost")
    public void createPost(@RequestBody PostsSaveRequestDto dto) {
        postsService.createPost(dto);
    }
}
