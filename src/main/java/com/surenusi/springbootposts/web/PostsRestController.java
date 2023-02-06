package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
import com.surenusi.springbootposts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostsRestController {

    private final PostsService postsService;

    @PostMapping("/post")
    public Long createPost(@RequestBody @Valid PostsSaveRequestDto requestDto) { return postsService.createPosts(requestDto); }

    @PutMapping("/post/{postId}")
    public Long updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.updatePosts(postId, requestDto);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable(name = "postId") Long postId) {
        postsService.deletePosts(postId);

        return;
    }
}
