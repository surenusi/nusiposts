package com.surenusi.springbootposts.dto.user;

import com.surenusi.springbootposts.common.Util;
import com.surenusi.springbootposts.domain.Authority;
import com.surenusi.springbootposts.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersInfoResponseDto {

    private String login;
    private String nickname;
    private Set<Authority> authorities;
    private String createdDate;
    private String modifiedDate;

    public UsersInfoResponseDto(Users users) {
        login = users.getLogin();
        nickname = users.getNickname();
        authorities = users.getAuthorities();
        createdDate = Util.toStringDateTime(users.getCreatedDate());
        modifiedDate = Util.toStringDateTime(users.getModifiedDate());
    }
}
