package com.dunowljj.book.security.jwt.exception;

import com.dunowljj.book.common.exception.ErrorCode;

public class AccessTokenException extends CustomTokenException {

    public AccessTokenException() {
        super(ErrorCode.ACCESS_TOKEN_AUTHENTICATION_FAIL);
    }

    public AccessTokenException(String message) {
        super(ErrorCode.ACCESS_TOKEN_AUTHENTICATION_FAIL, message);
    }

}
