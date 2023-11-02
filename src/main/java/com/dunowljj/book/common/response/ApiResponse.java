package com.dunowljj.book.common.response;

import com.dunowljj.book.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private Status status;

    private T data;

    public ApiResponse(ErrorType errorCode) {
        this.status = new Status(errorCode);
    }

    public ApiResponse(ErrorType errorCode, T data) {
        this.status = new Status(errorCode);
        this.data = data;
    }
}
