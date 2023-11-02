package com.dunowljj.book.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorType {

    HttpStatus getStatusCode();

    String getCode();

    String getMessage();

}
