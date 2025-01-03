package com.iambishal.spring_boot_rest_api_boilerplate.permission;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.permission.Permission;
import com.iambishal.spring_boot_rest_api_boilerplate.domain.permission.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Testcontainers
//@ActiveProfiles("test")
class PermissionRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.2")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private PermissionRepository permissionRepository;

    @BeforeEach
    void setUp() {
        // Preparing sample data
        permissionRepository.saveAll(List.of(
                Permission.builder()
                        .name("READ_USER")
                        .displayName("Read User")
                        .description("Allows reading user details")
                        .removable(true)
                        .build(),
                Permission.builder()
                        .name("WRITE_USER")
                        .displayName("Write User")
                        .description("Allows creating/updating user details")
                        .removable(true)
                        .build(),
                Permission.builder()
                        .name("DELETE_USER")
                        .displayName("Delete User")
                        .description("Allows deleting user details")
                        .removable(true)
                        .build()
        ));
    }

    @Test
    void testFindAll_withPagination() {
        // Arrange: Define pagination parameters
        PageRequest pageRequest = PageRequest.of(0, 2); // First page, 2 records per page

        // Act: Retrieve the paginated permissions
        Page<Permission> page = permissionRepository.findAll(pageRequest);

        // Assert: Verify the pagination and contents
        assertThat(page).isNotNull();
        assertThat(page.getTotalElements()).isEqualTo(3); // Total permissions
        assertThat(page.getTotalPages()).isEqualTo(2);    // Total pages (3 items / 2 per page)
        //assertThat(page.getContent()).has(3);        // Records on the first page
        assertThat(page.getContent().get(0).getName()).isEqualTo("READ_USER"); // Verify data
    }
}