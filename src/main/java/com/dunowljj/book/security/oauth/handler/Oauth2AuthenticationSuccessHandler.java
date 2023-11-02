package com.dunowljj.book.security.oauth.handler;

import com.dunowljj.book.security.jwt.dto.JwtUserClaimsDto;
import com.dunowljj.book.security.jwt.util.JwtUtils;
import com.dunowljj.book.security.oauth.OAuth2JwtUserDetails;
import com.dunowljj.book.security.oauth.dto.LoginResponseDto;
import com.dunowljj.book.domain.user.User;
import com.dunowljj.book.domain.user.UserRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final UserRespository userRespository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2JwtUserDetails oAuth2User = (OAuth2JwtUserDetails) authentication.getPrincipal();
        JwtUserClaimsDto jwtUserClaimsDto = JwtUserClaimsDto.of(oAuth2User);

        addTokensInHeader(response, jwtUserClaimsDto);

        /**
         * 로그인 정보를 응답에 반환하는 경우 -> 여기에 이전 url 넣어줘도 될듯
         */
        String email = oAuth2User.getEmail();
        User user = userRespository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), LoginResponseDto.of(user));

//        redirectToCachedUrl(request, response);
    }

    private void addTokensInHeader(HttpServletResponse response, JwtUserClaimsDto jwtUserClaimsDto) {
        String accessToken = JwtUtils.generateAccessToken(jwtUserClaimsDto);
        String refreshToken = JwtUtils.generateRefreshToken(jwtUserClaimsDto);

        response.addHeader(AUTH_HEADER, TOKEN_TYPE + accessToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Set-Cookie", createRefreshTokenCookie(refreshToken).toString());
        response.setCharacterEncoding("UTF-8");
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .maxAge(EXPIRATION)
                .path(HOME_URL)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

    // 인증 전 요청했던 요청 url 받아오기

    // 첫 유저인지
    // isExistUser -> 닉네임 입력 혹은 url로 요청
    // -> 저장된 url (없으면 : "/", 있으면 : 기존요청)
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
