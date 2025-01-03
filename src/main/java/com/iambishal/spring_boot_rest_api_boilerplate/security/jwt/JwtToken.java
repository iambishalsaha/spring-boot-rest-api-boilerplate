package com.iambishal.spring_boot_rest_api_boilerplate.security.jwt;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a JWT token stored in Redis.
 * <p>
 * `@RedisHash` annotation indicates that this class maps to a Redis Hash named "jwtTokens".
 */
@Getter
@Setter
@Builder
@RedisHash(value = "jwtTokens")
public class JwtToken {

    /**
     * Unique identifier for the token (auto-generated using GenerationType.UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Indexed field for faster retrieval by user ID.
     */
    @Indexed
    private UUID userId;

    /**
     * The actual access token string.
     */
    @Indexed
    private String accessToken;

    /**
     * The refresh token string.
     */
    @Indexed
    private String refreshToken;

    /**
     * Whether the "remember me" option was selected during login.
     */
    @Indexed
    private Boolean rememberMe;

    /**
     * The IP address of the user who generated the token.
     */
    @Indexed
    private String ipAddress;

    /**
     * The user agent of the user's browser.
     */
    @Indexed
    private String userAgent;

    /**
     * The timestamp (in milliseconds) of when the access token expires.
     *
     * @TimeToLive annotation defines the time-to-live for this field in milliseconds.
     */
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long accessTokenExpiresAt;
}
