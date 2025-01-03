package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * This exception is thrown when a client sends a request that the server cannot
 * process because the expectation in the request header is not met.
 * It maps to a 417 Expectation Failed HTTP status code.
 * <p>
 * `@ResponseStatus` annotation indicates that this exception should map to a specific HTTP status code.
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ExpectationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a default error message.
     */
    public ExpectationException() {
        super("Expectation exception!");
    }

    /**
     * Constructor with a default error message.
     */
    public ExpectationException(final String message) {
        super(message);
    }
}
