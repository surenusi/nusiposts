package com.surenusi.springbootposts.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersUpdateRequestDto {
    private String password;
    private String nickname;
    private String email;

    @Builder
    public UsersUpdateRequestDto(String password, String nickname, String email) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
