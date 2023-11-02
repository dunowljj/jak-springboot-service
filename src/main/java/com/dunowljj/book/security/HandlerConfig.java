package com.dunowljj.book.security;

import com.dunowljj.book.security.oauth.handler.Oauth2AuthenticationSuccessHandler;
import com.dunowljj.book.domain.user.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class HandlerConfig {
    private final UserRespository userRespository;

    @Bean
    public AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler() {
        return new Oauth2AuthenticationSuccessHandler(userRespository);
    }
}
