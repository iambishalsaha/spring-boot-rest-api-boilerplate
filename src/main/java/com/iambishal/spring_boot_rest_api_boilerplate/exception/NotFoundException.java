package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * This exception is thrown when a requested resource is not found on the server.
 * It maps to a 404 Not Found HTTP status code.
 * <p>
 * `@ResponseStatus` annotation indicates that this exception should map to a specific HTTP status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a default error message.
     */
    public NotFoundException() {
        super("Not found!");
    }

    /**
     * Constructor with a custom error message that can provide more specific details
     * about the missing resource.
     *
     * @param message the specific error message explaining what resource was not found
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
