package com.surenusi.springbootposts.dto.authority;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String login;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Builder
    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
