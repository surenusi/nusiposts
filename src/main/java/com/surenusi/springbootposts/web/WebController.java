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

    /**
     * 게시글 전체 조회
     * @param model
     * @return main.hbs
     */
    @GetMapping("/")
    public String readAllPosts(Model model) {
        model.addAttribute("posts", postsService.readAllPosts());
        return "main";
    }

    /**
     * 게시글 상세 조회
     * 조회수 증가
     * @param postId
     * @param model
     * @return viewPost.hbs
     */
    @GetMapping("/post/{postId}")
    public String readPost(@PathVariable(name = "postId") Long postId, Model model) {
        //조회수 증가
        postsService.updatePostsViewCount(postId);
        model.addAttribute("post", postsService.readPosts(postId));
        return "viewPost";
    }

//    @GetMapping("/user/info/{userId}")
//    public String readUsers(@PathVariable(name = "userId") Long userId, Model model) {
//        model.addAttribute("user", usersService.readUsers(userId));
//        return "userInfo";
//    }
}
