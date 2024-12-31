package com.iambishal.spring_boot_rest_api_boilerplate.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}