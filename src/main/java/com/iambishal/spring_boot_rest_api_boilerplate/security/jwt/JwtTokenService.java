package com.iambishal.spring_boot_rest_api_boilerplate.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;
}
