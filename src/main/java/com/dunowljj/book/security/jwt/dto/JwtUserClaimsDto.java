package com.dunowljj.book.security.jwt.dto;

import com.dunowljj.book.security.oauth.OAuth2JwtUserDetails;
import com.dunowljj.book.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
public class JwtUserClaimsDto {

    private String email;
    private Role role;

    public JwtUserClaimsDto(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public static JwtUserClaimsDto of(OAuth2JwtUserDetails userDetails) {
        return JwtUserClaimsDto.builder()
                .email(userDetails.getEmail())
                .role(userDetails.findAnyFirstRole())
                .build();
    }
}

