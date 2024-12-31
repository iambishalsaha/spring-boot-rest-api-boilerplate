package com.iambishal.spring_boot_rest_api_boilerplate.permission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Page<Permission> findAll(Pageable pageable);
}