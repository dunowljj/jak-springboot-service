package com.dunowljj.book.config.security.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class LoginResponseDto {
    private String email;
    private String nickname;
    private String userImg;

    public LoginResponseDto(String email, String nickname, String userImg) {
        this.email = email;
        this.nickname = nickname;
        this.userImg = userImg;
    }
}

