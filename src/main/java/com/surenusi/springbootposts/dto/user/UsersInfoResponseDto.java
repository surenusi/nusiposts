package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.common.Util;
import com.surenusi.springbootposts.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersInfoResponseDto {

    private Long id;
    private String login;
    private String password;
    private String nickname;
    private String email;
    private String createdDate;
    private String modifiedDate;

    public UsersInfoResponseDto(Users users) {
        id = users.getId();
        login = users.getLogin();
        password = users.getPassword();
        nickname = users.getNickname();
        email = users.getEmail();
        createdDate = Util.toStringDateTime(users.getCreatedDate());
        modifiedDate = Util.toStringDateTime(users.getModifiedDate());
    }
}
