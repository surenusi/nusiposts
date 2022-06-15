package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
