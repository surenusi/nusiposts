package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.service.PostsService;
import com.surenusi.springbootposts.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsService postsService;
    private final UsersService usersService;

    @PostMapping("/post")
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

    @PostMapping("/user")
    public Long createUsers(@RequestBody UsersSaveRequestDto requestDto) { return usersService.createUsers(requestDto); }

    @PutMapping("/user/{userId}")
    public Long updateUsers(@PathVariable(name = "userId") Long userId,
                            @RequestBody UsersUpdateRequestDto requestDto) {

        return usersService.updateUsers(userId, requestDto);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUsers(@PathVariable(name = "userId") Long userId) {
        usersService.deleteUsers(userId);
    }
}
