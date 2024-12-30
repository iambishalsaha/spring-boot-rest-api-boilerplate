package com.iambishal.spring_boot_rest_api_boilerplate.permission;

import com.iambishal.spring_boot_rest_api_boilerplate.role.Role;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Permission entity in the system, which defines specific access or actions
 * that can be assigned to roles.
 *
 * <p>Each Permission includes the following attributes:</p>
 * <ul>
 *   <li><b>name</b>: A unique identifier for the permission.</li>
 *   <li><b>displayName</b>: A human-readable name for the permission.</li>
 *   <li><b>description</b>: A textual description of the permission.</li>
 *   <li><b>removable</b>: A flag indicating whether the permission can be removed from the system.</li>
 * </ul>
 *
 * <p>The Permission entity has a many-to-many relationship with the Role entity.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "permissions",
        indexes = {
                @Index(name = "idx_permission_name", columnList = "name", unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_permission_name", columnNames = "name")
        }
)
public class Permission extends BaseEntity {

    /**
     * The unique name of the permission. This is used for internal identification.
     * It is indexed and constrained to be unique in the database.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * A user-friendly name for the permission, suitable for display in the UI.
     */
    @Column(name = "display_name", nullable = false)
    private String displayName;

    /**
     * A more detailed description of what the permission allows.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Indicates whether the permission can be removed from the system. Defaults to true.
     */
    @Column(name = "removable", nullable = false, columnDefinition = "boolean default true")
    private boolean removable;

    /**
     * A ManyToMany relationship with the Role entity. This represents the roles that have this permission.
     * Uses lazy fetching for performance optimization.
     */
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    /**
     * Adds a role to the set of roles associated with this permission.
     * Also ensures the relationship is updated in the Role entity.
     *
     * @param role the role to be added
     */
    public void addRole(Role role) {
        roles.add(role);
        role.getPermissions().add(this);
    }

    /**
     * Removes a role from the set of roles associated with this permission.
     * Also ensures the relationship is updated in the Role entity.
     *
     * @param role the role to be removed
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.getPermissions().remove(this);
    }
}
