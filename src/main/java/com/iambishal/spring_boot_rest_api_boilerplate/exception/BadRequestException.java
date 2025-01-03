package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * This exception is thrown when a client submits a request with invalid data or parameters
 * that violate business logic. It maps to a 400 Bad Request HTTP status code.
 * <p>
 * `@ResponseStatus` annotation indicates that this exception should map to a specific HTTP status code.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a default error message.
     */
    public BadRequestException() {
        super("Bad request!");
    }

    /**
     * Constructor that allows specifying a custom error message.
     *
     * @param message the custom error message
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
