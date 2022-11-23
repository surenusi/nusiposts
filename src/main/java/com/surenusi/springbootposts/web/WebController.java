package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PostsService postsService;

    @GetMapping("/")
    public String readAllPosts(Model model) {
        model.addAttribute("posts", postsService.readAllPosts());
        return "main";
    }

    @GetMapping("/view/")
    public String readPost(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("post", postsService.readPost(id));
        return "viewPost";
    }
}
