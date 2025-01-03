package com.iambishal.spring_boot_rest_api_boilerplate.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iambishal.spring_boot_rest_api_boilerplate.exception.AppExceptionHandler;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.response.ErrorResponse;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.service.MessageSourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is a Spring Security `AuthenticationEntryPoint` implementation.
 * It's invoked when an unauthorized user attempts to access a protected resource.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Injected service for message internationalization.
     */
    private final MessageSourceService messageSourceService;

    /**
     * ObjectMapper for converting objects to JSON format.
     */
    private final ObjectMapper objectMapper;

    /**
     * This method is called whenever an unauthorized user attempts to access a protected resource.
     * It extracts the specific reason for the authentication failure from the request attributes
     * and uses it to construct an appropriate error message.
     *
     * @param request       The HttpServletRequest object.
     * @param response      The HttpServletResponse object.
     * @param authException The AuthenticationException object that caused the failure.
     * @throws IOException If there's an error writing the error response to the response body.
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        final String expired = (String) request.getAttribute("expired");
        final String unsupported = (String) request.getAttribute("unsupported");
        final String invalid = (String) request.getAttribute("invalid");
        final String illegal = (String) request.getAttribute("illegal");
        final String notfound = (String) request.getAttribute("notfound");
        final String message;

        if (expired != null) {
            message = expired;
        } else if (unsupported != null) {
            message = unsupported;
        } else if (invalid != null) {
            message = invalid;
        } else if (illegal != null) {
            message = illegal;
        } else if (notfound != null) {
            message = notfound;
        } else {
            message = authException.getMessage();
        }

        log.error("Could not set user authentication in security context. Error: {}", message);

        // Create an ErrorResponse using the AppExceptionHandler and the retrieved message
        ResponseEntity<ErrorResponse> responseEntity = new AppExceptionHandler(messageSourceService)
                .handleBadCredentialsException(new BadCredentialsException(message));

        // Write the error response as JSON to the response body
        response.getWriter().write(objectMapper.writeValueAsString(responseEntity.getBody()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
