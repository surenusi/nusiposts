package com.surenusi.springbootposts.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersLoginRequestDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String login;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
