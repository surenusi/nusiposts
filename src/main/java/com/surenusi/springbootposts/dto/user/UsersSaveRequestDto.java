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

    public Users toEntity() {
        return Users.builder()
                .login(login)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
