package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import java.io.Serial;

/**
 * This exception class is used to handle errors related to encryption or decryption operations.
 * It inherits from `RuntimeException` and provides several constructors for different scenarios.
 * <p>
 * `@Serial` is used for serialization compatibility across JVM versions.
 */
public class CipherException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a default error message.
     */
    public CipherException() {
        super("Cipher exception!");
    }

    /**
     * Constructor with a custom error message.
     *
     * @param message the specific error message related to the cipher operation
     */
    public CipherException(final String message) {
        super(message);
    }

    /**
     * Constructor with a custom error message and a cause (underlying exception).
     *
     * @param message the specific error message
     * @param cause   the underlying exception that caused this exception
     */
    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with a cause (underlying exception).
     *
     * @param cause the underlying exception that caused this exception
     */
    public CipherException(Throwable cause) {
        super(cause);
    }

    /**
     * Full constructor with all parameters inherited from RuntimeException.
     * This constructor is typically used by the JVM internally.
     *
     * @param message            the specific error message
     * @param cause              the underlying exception that caused this exception
     * @param enableSuppression  whether or not suppression is enabled for this exception
     * @param writableStackTrace whether or not the stack trace can be written to a stream
     */
    protected CipherException(String message,
                              Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
