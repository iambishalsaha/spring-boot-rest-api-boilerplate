package com.iambishal.spring_boot_rest_api_boilerplate.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository1 extends JpaRepository<User, Long> {
}