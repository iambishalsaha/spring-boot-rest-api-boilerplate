package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown specifically when a refresh token has expired.
 * It extends `TokenExpiredException` and provides more specific information
 * about the type of token that has expired.
 * <p>
 * It maps to a 401 Unauthorized HTTP status code.
 * <p>
 * `@ResponseStatus` annotation indicates that this exception should map to a specific HTTP status code.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RefreshTokenExpiredException extends TokenExpiredException {
    public RefreshTokenExpiredException() {
        super("Refresh token is expired!");
    }

    public RefreshTokenExpiredException(final String message) {
        super(message);
    }
}
