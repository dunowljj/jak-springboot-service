package com.dunowljj.book.security.jwt.handler;

import com.dunowljj.book.common.response.ErrorResponse;
import com.dunowljj.book.security.jwt.exception.CustomAccessDeniedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CustomAccessDeniedException customAccessDeniedException = (CustomAccessDeniedException) accessDeniedException;

        response.setStatus(customAccessDeniedException.getErrorType().getStatusCode().value());

        ErrorResponse errorResponse = new ErrorResponse(customAccessDeniedException.getErrorType());
        ObjectMapper objectMapper = new ObjectMapper();
        String errorResponseData = objectMapper.writeValueAsString(errorResponse);

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(errorResponseData);
    }
}
