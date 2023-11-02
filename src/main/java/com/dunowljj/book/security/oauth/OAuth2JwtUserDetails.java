package com.dunowljj.book.security.oauth;

import com.dunowljj.book.security.oauth.dto.OAuthAttributes;
import com.dunowljj.book.security.jwt.dto.JwtUserClaimsDto;
import com.dunowljj.book.domain.user.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class OAuth2JwtUserDetails extends DefaultOAuth2User implements UserDetails {

    private final OAuthAttributes oAuthAttributes;

    public OAuth2JwtUserDetails(
            Collection<? extends GrantedAuthority> authorities,
            OAuthAttributes oAuthAttributes
    ) {
        super(authorities, oAuthAttributes.getAttributes(), oAuthAttributes.getNameAttributeKey());
        this.oAuthAttributes = oAuthAttributes;
    }

    public OAuth2JwtUserDetails(JwtUserClaimsDto userClaimsDto) {
        super(Collections.singleton(
                new SimpleGrantedAuthority(userClaimsDto.getRole().getKey())),
                null,
                null);
        this.oAuthAttributes = OAuthAttributes.builder()
                .email(userClaimsDto.getEmail())
                .build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return oAuthAttributes.getName();
    }

    public String getNickname() {
        return oAuthAttributes.getNickname();
    }

    public String getEmail() {
        return oAuthAttributes.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Role findAnyFirstRole() {
       return getAuthorities().stream()
                .map((grantedAuthority) -> Role.findRoleByKey(grantedAuthority.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("권한이 존재하지 않습니다.")); // todo: 중복적인 검증 로직
    }
}
