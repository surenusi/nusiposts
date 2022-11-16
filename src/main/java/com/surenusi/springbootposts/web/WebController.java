package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PostsService postsService;

    @GetMapping("/")
    public String readAllPosts(Model model) {
        model.addAttribute("posts", postsService.readAllPosts());
        return "main";
    }
}
