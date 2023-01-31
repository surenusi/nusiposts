package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
import com.surenusi.springbootposts.dto.user.UsersInfoResponseDto;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.service.PostsService;
import com.surenusi.springbootposts.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsService postsService;
    private final UsersService usersService;

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

    @PostMapping("/user")
    public Long createUsers(@RequestBody @Valid UsersSaveRequestDto requestDto) { return usersService.createUsers(requestDto); }

    @PutMapping("/user/{userId}")
    public Long updateUsers(@PathVariable(name = "userId") Long userId,
                            @RequestBody UsersUpdateRequestDto requestDto) {

        return usersService.updateUsers(userId, requestDto);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUsers(@PathVariable(name = "userId") Long userId) {
        usersService.deleteUsers(userId);
    }

    //유저 아이디 중복 체크
    @GetMapping("/user/loginCheck/{userLogin}")
    public int checkUsersLoginOverlap(@PathVariable(name = "userLogin") String userLogin) {
        return usersService.countUsers("login", userLogin);
    }

    //유저 이메일 중복 체크
    @GetMapping("/user/emailCheck/{userEmail}")
    public int checkUsersEmailOverlap(@PathVariable(name = "userEmail") String userEmail) {
        return usersService.countUsers("email", userEmail);
    }
}
