package com.dunowljj.book.security.jwt.authentication;

import com.dunowljj.book.security.jwt.dto.JwtUserClaimsDto;
import com.dunowljj.book.security.jwt.util.JwtUtils;
import com.dunowljj.book.security.oauth.OAuth2JwtUserDetails;
import com.dunowljj.book.domain.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = (String) authentication.getCredentials();

        JwtUtils.validateToken(jwt);

        OAuth2JwtUserDetails userDetails = getJwtUserDetails(jwt);
        return new JwtAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
    }

    private OAuth2JwtUserDetails getJwtUserDetails(String jwt) throws JwtException {
        Claims claims = JwtUtils.getClaims(jwt);

        JwtUserClaimsDto userClaimsDto = JwtUserClaimsDto.builder()
                .email((String) claims.get("email"))
                .role((Role) claims.get("role"))
                .build();

        return new OAuth2JwtUserDetails(userClaimsDto);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
