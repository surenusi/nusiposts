package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
import com.surenusi.springbootposts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsService postsService;

    @PostMapping("/createPost")
    public Long createPost(@RequestBody PostsSaveRequestDto dto) { return postsService.createPost(dto); }

    @PutMapping("/post/{postId}")
    public Long updatePost(@PathVariable(name = "postId") Long id, @RequestBody PostsUpdateRequestDto dto) {
        return postsService.updatePost(id, dto);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable(name = "postId") Long id) {
        postsService.deletePost(id);

        return;
    }
}
