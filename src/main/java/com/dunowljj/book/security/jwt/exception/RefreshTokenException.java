package com.dunowljj.book.security.jwt.exception;

import com.dunowljj.book.common.exception.ErrorCode;

public class RefreshTokenException extends CustomTokenException {

    public RefreshTokenException() {
        super(ErrorCode.REFRESH_TOKEN_AUTHENTICATION_FAIL);
    }

    public RefreshTokenException(String message) {
        super(ErrorCode.REFRESH_TOKEN_AUTHENTICATION_FAIL, message);
    }

}
