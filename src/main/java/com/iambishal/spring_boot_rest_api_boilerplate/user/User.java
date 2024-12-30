package com.iambishal.spring_boot_rest_api_boilerplate.user;

import com.iambishal.spring_boot_rest_api_boilerplate.role.Role;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_email", columnList = "email", unique = true),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = "email"),
        }
)
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_token_expiry")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime resetPasswordTokenExpiry;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
}
