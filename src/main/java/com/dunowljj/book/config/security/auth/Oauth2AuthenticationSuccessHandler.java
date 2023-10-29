package com.dunowljj.book.config.security.auth;

import com.dunowljj.book.config.security.jwt.JwtUtils;
import com.dunowljj.book.config.security.jwt.dto.JwtUserClaimsDto;
import com.dunowljj.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final String TOKEN_TYPE = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";
    private static final String HOME_URL = "/";
    private final String REFRESH_TOKEN = "refreshToken";
    private final int EXPIRATION = 60 * 60 * 24 * 30;
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2UserDetails oAuth2User = (OAuth2UserDetails) authentication.getPrincipal();

        Role role = oAuth2User.findAnyFirstRole();
        String email = oAuth2User.getEmail();

        JwtUserClaimsDto jwtUserClaimsDto = JwtUserClaimsDto.builder()
                .role(role)
                .email(email)
                .build();

        // 토큰 생성 및 추가 로직
        String accessToken = JwtUtils.generateAccessToken(jwtUserClaimsDto);
        String refreshToken = JwtUtils.generateRefreshToken(jwtUserClaimsDto);

        response.addHeader(AUTH_HEADER, TOKEN_TYPE + accessToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Set-Cookie", getRefreshTokenCookie(refreshToken).toString());
        response.setCharacterEncoding("UTF-8");

        /**
         * 로그인 정보를 응답에 반환하는 경우 -> 여기에 이전 url 넣어줘도 될듯
         */
        /*User user = userRespository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .userImg(user.getPicture())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), loginResponseDto);*/

        redirectToCachedUrl(request, response);
    }

    private ResponseCookie getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .maxAge(EXPIRATION)
                .path(HOME_URL)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

    // 인증 전 요청했던 요청 url 받아오기
    private void redirectToCachedUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            response.sendRedirect(targetUrl);
        } else {
            // 세션 만료 대비
            response.sendRedirect(HOME_URL);
        }
    }
}
