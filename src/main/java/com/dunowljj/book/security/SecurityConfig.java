package com.dunowljj.book.security;

import com.dunowljj.book.security.jwt.authentication.JwtAuthenticationProvider;
import com.dunowljj.book.security.jwt.filter.JwtAccessTokenAuthenticationFilter;
import com.dunowljj.book.security.jwt.filter.JwtRefreshTokenAuthenticationFilter;
import com.dunowljj.book.security.jwt.handler.JwtAuthenticationEntryPoint;
import com.dunowljj.book.security.oauth.CustomOAuth2UserService;
import com.dunowljj.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@Configuration
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig  {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;

    private final CustomOAuth2UserService customOAuth2UserService;


    private final List<String> permitJwtUrlList = new ArrayList<>(
            List.of(
                    "/",
                    "/login/oauth2/code/.*",
                    "/oauth2/authorization/.*",
                    "/api/users/token",
                    "/health",
                    "/ws/.*",
                    "/chat/.*"
            ));
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .addFilterBefore(jwtAccessTokenAuthenticationFilter(), OAuth2AuthorizationRequestRedirectFilter.class)
                    .addFilterAfter(jwtRefreshTokenAuthenticationFilter(), JwtAccessTokenAuthenticationFilter.class)
                    .authorizeHttpRequests()
//                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/events/**").hasRole(Role.GUEST.name())
//                .antMatchers("/api/events/")
                    .anyRequest().authenticated()
//                .and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint())
//                    .accessDeniedHandler(customAccessDeniedHandler())
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                    .successHandler(oauth2AuthenticationSuccessHandler)
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


//    @Bean
//    public AuthenticationProvider jwtRefreshTokenAuthenticationProvider() {
//        return new JwtRefreshTokenAuthenticationProvider();
//    }
    @Bean
    public JwtAccessTokenAuthenticationFilter jwtAccessTokenAuthenticationFilter() throws Exception {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider());
        return new JwtAccessTokenAuthenticationFilter(authenticationManager(), jwtAuthenticationEntryPoint(), permitJwtUrlList);
    }

    @Bean
    public AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    public JwtRefreshTokenAuthenticationFilter jwtRefreshTokenAuthenticationFilter() throws Exception {
        return new JwtRefreshTokenAuthenticationFilter(authenticationManager(), jwtAuthenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }
}

/**
 * WebSecurityConfigurerAdapter가 deprecated 되어버렸다.
 * 공식 문서 내용
 * "Use a SecurityFilterChain Bean to configure HttpSecurity or a WebSecurityCustomizer Bean to configure WebSecurity"
 */