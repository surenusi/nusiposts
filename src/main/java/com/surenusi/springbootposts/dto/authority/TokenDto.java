package com.surenusi.springbootposts.dto.authority;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String token;

    @Builder
    public TokenDto(String token) {
        this.token = token;
    }
}
