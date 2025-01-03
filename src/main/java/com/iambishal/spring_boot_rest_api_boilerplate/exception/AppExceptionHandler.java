package com.iambishal.spring_boot_rest_api_boilerplate.exception;

import com.iambishal.spring_boot_rest_api_boilerplate.shared.response.DetailedErrorResponse;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.response.ErrorResponse;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.service.MessageSourceService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for handling exceptions thrown throughout the application
 * and providing appropriate HTTP responses with informative error messages.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AppExceptionHandler {

    /**
     * Injected service for message internationalization.
     */
    private final MessageSourceService messageSourceService;

    /**
     * Handles `HttpRequestMethodNotSupportedException` exceptions.
     * This exception occurs when an unsupported HTTP request method is used (e.g., PUT for a GET endpoint).
     *
     * @param e The exception object.
     * @return A ResponseEntity containing an error response with the appropriate HTTP status code and message.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, messageSourceService.getMessage("METHOD_NOT_ALLOWED"));
    }

    /**
     * Handles `HttpMessageNotReadableException` exceptions.
     * This exception occurs when the request body cannot be parsed (e.g., invalid JSON format).
     *
     * @param e The exception object.
     * @return A ResponseEntity containing an error response with the appropriate HTTP status code and message.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(final HttpMessageNotReadableException e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.BAD_REQUEST, messageSourceService.getMessage("INVALID_JSON_FORMAT"));
    }

    /**
     * Handles `BindException` exceptions.
     * This exception occurs when validation errors are found in request parameters.
     *
     * @param e The exception object.
     * @return A ResponseEntity containing an error response with the appropriate HTTP status code, message,
     * and detailed validation errors.
     */
    @ExceptionHandler(BindException.class)
    public final ResponseEntity<ErrorResponse> handleBindException(final BindException e) {
        log.error(e.toString(), e.getMessage());

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldname, message);
        });

        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, messageSourceService.getMessage("VALIDATION_ERROR"), errors);
    }

    /**
     * Handles various bad request exceptions.
     *
     * @param e Exception caused by invalid request parameters or payload.
     * @return ResponseEntity with BAD_REQUEST status and an error message.
     */
    @ExceptionHandler({
            BadRequestException.class,
            MultipartException.class,
            MissingServletRequestPartException.class,
            HttpMediaTypeNotSupportedException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            InvalidDataAccessApiUsageException.class,
            ConstraintViolationException.class,
            MissingRequestHeaderException.class,
            MalformedJwtException.class,
            CipherException.class,
    })
    public final ResponseEntity<ErrorResponse> handleBadRequestException(final Exception e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getCause() != null ? e.getCause().getMessage() : e.getMessage()
        );
    }

    /**
     * Handles exceptions for expired tokens.
     *
     * @param e Exception for token expiration.
     * @return ResponseEntity with UNAUTHORIZED status and an error message.
     */
    @ExceptionHandler({TokenExpiredException.class, RefreshTokenExpiredException.class})
    public final ResponseEntity<ErrorResponse> handleTokenExpiredException(final Exception e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
    }

    /**
     * Handles exceptions when a requested resource is not found.
     *
     * @param e Exception for resource not found.
     * @return ResponseEntity with NOT_FOUND status and an error message.
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * Handles authentication-related exceptions such as invalid credentials.
     *
     * @param e Exception for authentication failure.
     * @return ResponseEntity with UNAUTHORIZED status and an error message.
     */
    @ExceptionHandler({
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            AuthenticationCredentialsNotFoundException.class
    })
    public final ResponseEntity<ErrorResponse> handleBadCredentialsException(final Exception e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * Handles access-denied exceptions caused by insufficient permissions.
     *
     * @param e Exception for access denial.
     * @return ResponseEntity with FORBIDDEN status and an error message.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponse> handleAccessDeniedException(final Exception e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * Handles specific expectation-related exceptions.
     *
     * @param e Exception for failed expectations.
     * @return ResponseEntity with EXPECTATION_FAILED status and an error message.
     */
    @ExceptionHandler(ExpectationException.class)
    public final ResponseEntity<ErrorResponse> handleExpectationException(final Exception e) {
        log.error(e.toString(), e.getMessage());

        return buildErrorResponse(HttpStatus.EXPECTATION_FAILED, e.getMessage());
    }

    /**
     * Handles all uncaught exceptions not handled by specific handlers.
     *
     * @param e Generic exception.
     * @return ResponseEntity with INTERNAL_SERVER_ERROR status and a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(final Exception e) {
        log.error("Exception: {}", ExceptionUtils.getStackTrace(e));

        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, messageSourceService.getMessage("INTERNAL_SERVER_ERROR"));
    }

    /**
     * Helper method to build a response entity with an error message.
     *
     * @param httpStatus HTTP status to be returned.
     * @param message    Error message to be included in the response.
     * @return ResponseEntity with the error details.
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(final HttpStatus httpStatus, final String message) {
        return buildErrorResponse(httpStatus, message, new HashMap<>());
    }

    /**
     * Helper method to build a response entity with detailed error information.
     *
     * @param httpStatus HTTP status to be returned.
     * @param message    Error message to be included in the response.
     * @param errors     Map containing detailed error messages for specific fields.
     * @return ResponseEntity with detailed error information.
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            final HttpStatus httpStatus,
            final String message,
            final Map<String, String> errors
    ) {
        if (!errors.isEmpty()) {
            return ResponseEntity.status(httpStatus).body(
                    DetailedErrorResponse.builder().message(message).items(errors).build()
            );
        }

        return ResponseEntity.status(httpStatus).body(
                ErrorResponse.builder().message(message).build()
        );
    }
}
