package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.domain.Posts;
import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.repository.PostsRepository;
import com.surenusi.springbootposts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * API 테스트를 위해 작성되었음
 */
@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody UsersSaveRequestDto dto) {
        usersRepository.save(dto.toEntity());
    }
}
