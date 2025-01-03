package com.iambishal.spring_boot_rest_api_boilerplate.domain.user;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.role.Role;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a User entity within the application. It stores user-related information
 * and manages the relationship between users and roles.
 *
 * <p>Each User includes the following attributes:</p>
 * <ul>
 *   <li><b>firstName</b>: The user's first name.</li>
 *   <li><b>lastName</b>: The user's last name (optional).</li>
 *   <li><b>email</b>: The user's unique email address (used for identification and login).</li>
 *   <li><b>password</b>: The user's encrypted password.</li>
 *   <li><b>resetPasswordToken</b>: A token for password reset functionality (optional).</li>
 *   <li><b>resetPasswordTokenExpiry</b>: The expiration timestamp for the password reset token.</li>
 * </ul>
 *
 * <p>The User entity has a many-to-many relationship with the Role entity, enabling role-based access control.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
        },
        indexes = {
                @Index(name = "idx_users_first_name", columnList = "first_name", unique = true),
                @Index(name = "idx_users_last_name", columnList = "last_name", unique = true),
        }
)
public class User extends BaseEntity {

    /**
     * The user's first name. This field is mandatory.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The user's last name. This field is optional.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The user's email address. This is used as the user's login identifier and must be unique.
     * It is indexed and constrained to be unique in the database.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The user's password. This will be stored securely.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * A token used for resetting the user's password.
     */
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /**
     * The expiry date and time for the reset password token.
     */
    @Column(name = "reset_password_token_expiry")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime resetPasswordTokenExpiry;

    /**
     * A ManyToMany relationship with the Role entity.
     * This represents the roles assigned to the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_user_roles_user_id",
                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_user_roles_role_id",
                            foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE"
                    )
            ),
            uniqueConstraints = {
                    @UniqueConstraint(
                            name = "UK_user_roles_user_id_role_id",
                            columnNames = {"user_id", "role_id"}
                    )
            }
    )
    private List<Role> roles = new ArrayList<>();
}
