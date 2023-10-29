package com.dunowljj.book.config.security.jwt.dto;

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
}

