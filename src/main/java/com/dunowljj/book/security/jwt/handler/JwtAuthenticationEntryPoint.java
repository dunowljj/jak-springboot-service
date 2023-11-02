package com.dunowljj.book.security.jwt.handler;

import com.dunowljj.book.common.response.ErrorResponse;
import com.dunowljj.book.security.jwt.exception.CustomTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        CustomTokenException tokenException = (CustomTokenException) authException;

        response.setStatus(tokenException.getErrorType().getStatusCode().value());

        ErrorResponse errorResponse = new ErrorResponse(tokenException.getErrorType());
        ObjectMapper objectMapper = new ObjectMapper();
        String errorResponseData = objectMapper.writeValueAsString(errorResponse);

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(errorResponseData);
    }
}
