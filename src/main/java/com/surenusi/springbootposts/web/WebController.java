package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.service.PostsService;
import com.surenusi.springbootposts.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PostsService postsService;
    private final UsersService usersService;

    @GetMapping("/")
    public String readAllPosts(Model model) {
        model.addAttribute("posts", postsService.readAllPosts());
        return "main";
    }

    @GetMapping("/post/{postId}")
    public String readPost(@PathVariable(name = "postId") Long postId, Model model) {
        model.addAttribute("post", postsService.readPosts(postId));
        return "viewPost";
    }

    @GetMapping("/user/{userId}")
    public String readUsers(@PathVariable(name = "userId") Long userId, Model model) {
        model.addAttribute("user", usersService.readUsers(userId));
        return "userInfo";
    }
}
