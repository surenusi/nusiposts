package com.surenusi.springbootposts.dto.authority;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String accessToken;

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
