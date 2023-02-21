package com.surenusi.springbootposts.web;

import com.surenusi.springbootposts.dto.user.*;
import com.surenusi.springbootposts.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UsersRestController {

    private final UsersService usersService;

    //유저 회원가입
    @PostMapping()
    public Long usersSignUp(@RequestBody @Valid UsersSaveRequestDto requestDto) {
        return usersService.createUsers(requestDto);
    }

//  유저 권한 검증 필요
//    @PutMapping("/{userId}")
//    public Long updateUsers(@PathVariable(name = "userId") Long userId,
//                            @RequestBody UsersUpdateRequestDto requestDto) {
//        return usersService.updateUsers(userId, requestDto);
//    }

//    @DeleteMapping("/{userId}")
//    public void deleteUsers(@PathVariable(name = "userId") Long userId) {
//        usersService.deleteUsers(userId);
//    }

    //유저 아이디 중복 체크
    @GetMapping("/loginCheck/{userLogin}")
    public int checkUsersLoginOverlap(@PathVariable(name = "userLogin") String userLogin) {
        return usersService.countUsers("login", userLogin);
    }

    //유저 이메일 중복 체크
    @GetMapping("/emailCheck/{userEmail}")
    public int checkUsersEmailOverlap(@PathVariable(name = "userEmail") String userEmail) {
        return usersService.countUsers("email", userEmail);
    }

    //유저 정보 조회(개인)
    @GetMapping("/info")
    public ResponseEntity<UsersInfoResponseDto> getMyUsersInfo() {
        UsersInfoResponseDto responseDto = new UsersInfoResponseDto(usersService.getMyUsersWithAuthorities().get());
        return ResponseEntity.ok(responseDto);
    }

    //유저 정보 조회(관리자)
    @GetMapping("/info/{login}")
    public ResponseEntity<UsersInfoResponseDto> getUsersInfo(@PathVariable(name = "login") String login) {
        UsersInfoResponseDto responseDto = new UsersInfoResponseDto(usersService.getUsersWithAuthorities(login).get());
        return ResponseEntity.ok(responseDto);
    }
}
