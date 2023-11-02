package com.dunowljj.book.security.jwt.filter;

import com.dunowljj.book.security.jwt.authentication.JwtAuthenticationToken;
import com.dunowljj.book.security.jwt.dto.JwtUserClaimsDto;
import com.dunowljj.book.security.jwt.exception.RefreshTokenException;
import com.dunowljj.book.security.jwt.util.JwtUtils;
import com.dunowljj.book.security.oauth.OAuth2JwtUserDetails;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtRefreshTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final String REQUEST_TOKEN_REGENERATE_URL = "/api/users/token";

    private static final String TOKEN_TYPE = "Bearer ";

    private static final String AUTH_HEADER = "Authorization";

    private final String PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!isAccessTokenExpired(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            OAuth2JwtUserDetails userDetails = (OAuth2JwtUserDetails) authentication.getPrincipal();
            String accessToken = JwtUtils.generateAccessToken(JwtUserClaimsDto.of(userDetails));
            addTokenInHeader(response, accessToken);

            filterChain.doFilter(request, response);

        } catch (AuthenticationException authenticationException) {
            authenticationEntryPoint.commence(request, response, authenticationException);
        } catch (JwtException jwtException) {
            authenticationEntryPoint.commence(request, response, new RefreshTokenException(jwtException.getMessage()));
        }
    }

    private boolean isAccessTokenExpired(HttpServletRequest request) {
        return REQUEST_TOKEN_REGENERATE_URL.equals(request.getRequestURI());
    }

    private static void addTokenInHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + accessToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
    }

    private Authentication authenticate(HttpServletRequest request) {
        String jwt = validAndGetAccessToken(request);
        Authentication authentication = authenticationManager.authenticate(new JwtAuthenticationToken(jwt));
        return authentication;
    }

    private String validAndGetAccessToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        checkHasToken(jwt);
        checkTokenType(jwt);

        return jwt.substring(PREFIX.length());
    }

    private static void checkHasToken(String jwt) {
        if (jwt == null) {
            throw new RefreshTokenException("RefreshToken이 존재하지 않습니다.");
        }
    }

    private void checkTokenType(String jwt) {
        if (jwt != null && !jwt.startsWith(PREFIX)) {
            throw new RefreshTokenException("RefreshToken의 타입이 유효하지 않습니다.");
        }
    }
}
