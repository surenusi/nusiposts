package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.dto.user.UsersLoginRequestDTO;
import com.surenusi.springbootposts.dto.user.UsersLoginResponseDTO;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersRestController {

    private final UsersService usersService;

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

    //유저 로그인
    @PostMapping("/user/login")
    public UsersLoginResponseDTO loginUsers(@Valid UsersLoginRequestDTO requestDTO) {
        try{
            //로그인 성공
            return new UsersLoginResponseDTO(
                    usersService.loginUsers(requestDTO.getLogin(), requestDTO.getPassword())
            );
        }
        catch(Exception e) {
            //로그인 실패
            return null;
        }
    }
}
