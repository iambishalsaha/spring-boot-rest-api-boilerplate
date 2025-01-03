package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an authentication token (e.g., JWT) has expired.
 * It maps to a 401 Unauthorized HTTP status code.
 * <p>
 * `@ResponseStatus` annotation indicates that this exception should map to a specific HTTP status code.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token is expired!");
    }

    public TokenExpiredException(final String message) {
        super(message);
    }
}
