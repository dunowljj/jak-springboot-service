package com.dunowljj.book.security.oauth.dto;

import com.dunowljj.book.domain.user.User;
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

    public static LoginResponseDto of(User user) {
        return LoginResponseDto.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .userImg(user.getPicture())
                .build();
    }
}

