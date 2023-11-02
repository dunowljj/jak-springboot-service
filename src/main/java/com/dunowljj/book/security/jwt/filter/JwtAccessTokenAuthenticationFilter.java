package com.dunowljj.book.security.jwt.filter;

import com.dunowljj.book.security.jwt.authentication.JwtAuthenticationToken;
import com.dunowljj.book.security.jwt.exception.AccessTokenException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.regex.Pattern;

//@Component
@RequiredArgsConstructor
public class JwtAccessTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final List<String> permitUrlList;

    private final String PREFIX = "Bearer ";

    /**
     * JwtException이 발생하는 경우
     * - Provider 검증 로직에서 parse할때 던져지는 예외. 즉, jwt의 유효성 검증에 실패한 경우
     * - jwt 토큰이 존재하지 않거나, 타입이 Bearer가 아닐때
     *
     * AccessToken, RefreshToken에 사용하는 Provider의 기능이 완전히 같아서 1개의 Provider만 사용했습니다.
     * 그렇다보니 Provider에서 발생한 예외가 AccessToken에서 발생한 예외인지, RefreshToken에서 발생한 예외인지 구분이 필요했습니다.
     * AccessTokenException과 RefreshTokenException을 구현했는데, 기존 메시지를 집어넣어 되던졌습니다.
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isStaticResources(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isPermitted(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authenticate = authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (AuthenticationException authenticationException) {
            authenticationEntryPoint.commence(request, response, authenticationException);
        } catch (JwtException jwtException) {
            authenticationEntryPoint.commence(request, response, new AccessTokenException(jwtException.getMessage()));
        }

        filterChain.doFilter(request, response);
    }

    private boolean isStaticResources(String requestURI) {
        List<String> staticUrlPatterns = List.of("/js/.*", "/images/.*", "/css/.*");

        for (String pattern : staticUrlPatterns) {
            if (Pattern.matches(pattern, requestURI)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPermitted(String requestUri) {
        for (String pattern : permitUrlList) {
            if (Pattern.matches(pattern, requestUri)) {
                return true;
            }
        }
        return false;
    }

    private Authentication authenticate(HttpServletRequest request) {
        String jwt = validateAndGetAccessToken(request);
        Authentication authentication = authenticationManager.authenticate(new JwtAuthenticationToken(jwt));
        return authentication;
    }

    private String validateAndGetAccessToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        checkHasToken(jwt);
        checkTokenValidation(jwt);

        return jwt.substring(PREFIX.length());
    }

    private static void checkHasToken(String jwt) {
        if (jwt == null) {
            throw new AccessTokenException("AccessToken이 존재하지 않습니다.");
        }
    }

    private void checkTokenValidation(String jwt) {
        if (jwt != null && !jwt.startsWith(PREFIX)) {
            throw new AccessTokenException("AccessToken의 타입이 유효하지 않습니다.");
        }
    }
}

