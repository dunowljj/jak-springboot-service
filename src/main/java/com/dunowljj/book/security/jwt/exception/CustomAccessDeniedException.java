package com.dunowljj.book.security.jwt.exception;

import com.dunowljj.book.common.exception.ErrorCode;
import com.dunowljj.book.common.exception.ErrorData;
import com.dunowljj.book.common.exception.ErrorType;
import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;

@Getter
public class CustomAccessDeniedException extends AccessDeniedException {

    private final ErrorType errorType;
    private ErrorData errorData;

    public CustomAccessDeniedException(ErrorCode errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CustomAccessDeniedException(ErrorCode errorCode, String message) {
        super(errorCode.changeMessage(message).getMessage());
        this.errorType = errorCode.changeMessage(message);
    }

    public CustomAccessDeniedException(ErrorCode errorType, ErrorData errorData) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.errorData = errorData;
    }
}
