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
    public String readPost(@PathVariable(name = "postId") Long id, Model model) {
        model.addAttribute("post", postsService.readPost(id));
        return "viewPost";
    }

    @GetMapping("/user/{userId}")
    public String readUsers(@PathVariable(name = "usersId") Long userId, Model model) {
        model.addAttribute("users", usersService.readUsers(userId));
        return "userInfo";
    }
}
