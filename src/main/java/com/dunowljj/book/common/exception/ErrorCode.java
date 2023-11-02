package com.dunowljj.book.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode implements ErrorType {

    SUCCESS(HttpStatus.OK, "200", "OK"),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "401", "아이디와 비밀번호를 확인 해주세요"),
    UNAUTHORIZED_USER_ACCESS(HttpStatus.UNAUTHORIZED, "401", "인가되지 않은 유저의 접근입니다."),
    FAIL_SIGNUP(HttpStatus.BAD_REQUEST, "400", "회원가입에 실패 했습니다"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "404", "회원를 찾을 수 없습니다"),
    ACCESS_TOKEN_AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "401", "인증에 실패 했습니다"),
    REFRESH_TOKEN_AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "401", "인증에 실패 했습니다"),
    INSUFFICIENT_PRIVILEGES(HttpStatus.FORBIDDEN, "401", "접근 권한이 부족합니다."),
    ;

    @JsonIgnore
    private final HttpStatus statusCode;
    private final String code;
    private String message;

    ErrorCode(
            HttpStatus statusCode,
            String code,
            String message
    ) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }

    public ErrorResult changeMessage(
            String message
    ) {
        return new ErrorResult(this.statusCode, this.getCode(), message);
    }

}

