package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.domain.Users;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSaveRequestDto {

    private String login;
    private String password;
    private String nickname;
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
                .build();
    }
}
