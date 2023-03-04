package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersLoginResponseDTO {

    private String login;
    private String nickname;

    public UsersLoginResponseDTO(Users users) {
        this.login = users.getLogin();
        this.nickname = users.getNickname();
    }
}
