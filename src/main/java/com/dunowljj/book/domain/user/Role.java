package com.dunowljj.book.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

    public static Role findRoleByKey(String key) {
        for (Role role : Role.values()) {
            if (key.equals(role.getKey())) {
                return role;
            }
        }

        return Role.GUEST;
    }
}
