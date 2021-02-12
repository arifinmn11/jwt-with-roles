package com.arifinmn.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationExceptions {
    public UnauthorizedException(HttpStatus status, String message) {
        super( HttpStatus.UNAUTHORIZED, message);
    }
}
