package com.dunowljj.book.security.jwt.exception;

import com.dunowljj.book.common.exception.ErrorCode;
import com.dunowljj.book.common.exception.ErrorData;
import com.dunowljj.book.common.exception.ErrorType;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public abstract class CustomTokenException extends AuthenticationException {

    private final ErrorType errorType;
    private ErrorData errorData;


    public CustomTokenException(ErrorCode errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CustomTokenException(ErrorCode errorCode, String message) {
        super(errorCode.changeMessage(message).getMessage());
        this.errorType = errorCode.changeMessage(message);
    }

    public CustomTokenException(ErrorCode errorType, ErrorData errorData) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.errorData = errorData;
    }

}
