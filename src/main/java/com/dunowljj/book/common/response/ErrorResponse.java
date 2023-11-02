package com.dunowljj.book.common.response;

import com.dunowljj.book.common.exception.ErrorData;
import com.dunowljj.book.common.exception.ErrorType;

public class ErrorResponse extends ApiResponse<ErrorData> {

    public ErrorResponse(ErrorType errorCode) {
        super(errorCode);
    }

    public ErrorResponse(ErrorType errorCode, ErrorData errorData) {
        super(errorCode, errorData);
    }

}
