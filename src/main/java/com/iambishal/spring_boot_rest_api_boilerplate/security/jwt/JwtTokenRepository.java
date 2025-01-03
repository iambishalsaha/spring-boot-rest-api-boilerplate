package com.iambishal.spring_boot_rest_api_boilerplate.security.jwt;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JwtTokenRepository extends CrudRepository<JwtToken, UUID> {
}
