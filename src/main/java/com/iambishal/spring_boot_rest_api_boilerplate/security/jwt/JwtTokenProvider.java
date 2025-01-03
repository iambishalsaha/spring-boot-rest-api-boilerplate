package com.iambishal.spring_boot_rest_api_boilerplate.security.jwt;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    final String appSecret;

    @Getter
    private final Long accessTokenExpiresIn;

    @Getter
    private Long refreshTokenExpiresIn;

    private final Long rememberMeTokenExpiresIn;

    private final UserService userService;

    private final JwtTokenService jwtTokenService;

    private final HttpServletRequest httpServletRequest;

    public JwtTokenProvider(
            @Value("${app.secret}") final String appSecret,
            @Value("${app.jwt.access-token.expires-in}") final Long accessTokenExpiresIn,
            @Value("${app.jwt.refresh-token.expires-in}") final Long refreshTokenExpiresIn,
            @Value("${app.jwt.remember-me.expires-in}") final Long rememberMeTokenExpiresIn,
            final UserService userService,
            final JwtTokenService jwtTokenService,
            final HttpServletRequest httpServletRequest
    ) {
        this.userService = userService;
        this.appSecret = appSecret;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.rememberMeTokenExpiresIn = rememberMeTokenExpiresIn;
        this.jwtTokenService = jwtTokenService;
        this.httpServletRequest = httpServletRequest;
    }

    public void setRememberMeRefreshTokenExpiresIn() {
        this.refreshTokenExpiresIn = rememberMeTokenExpiresIn;
    }

    public String generateAccessToken(final String subject) {
        return generateToken(subject, accessTokenExpiresIn);
    }

    public String generateRefreshToken(final String subject) {
        return generateToken(subject, refreshTokenExpiresIn);
    }

    public String generateToken(final String subject, final Long expiration) {
        Instant currentTime = Instant.now();
        Instant jwtExpiratyTime = currentTime.plusMillis(expiration);

        return Jwts.builder()
                .subject(subject)
                .issuer("")
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(jwtExpiratyTime))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(appSecret.getBytes());
    }
}
