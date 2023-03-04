package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.domain.Users;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSaveRequestDto {

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{6,12}")
    private String login;

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9`~!@#$%^&*()-_=+]{6,24}")
    private String password;

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9ㄱ-ㅎ가-힣]{2,20}")
    private String nickname;

    @NotBlank
    @Email
    private String email;

    @Builder
    public UsersSaveRequestDto(String login, String password, String nickname, String email) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public Users toEntity() {
        return Users.builder()
                .login(login)
                .password(password)
                .nickname(nickname)
                .email(email)
                .activated(true)
                .build();
    }
}
