package com.iambishal.spring_boot_rest_api_boilerplate.role;

import com.iambishal.spring_boot_rest_api_boilerplate.permission.Permission;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a Role entity within the application,
 * which groups permissions and assigns them to users or entities.
 *
 * <p>Each Role includes the following attributes:</p>
 * <ul>
 *   <li><b>name</b>: A unique identifier for the role.</li>
 *   <li><b>displayName</b>: A human-readable name for the role.</li>
 *   <li><b>description</b>: A textual description of the role's purpose.</li>
 *   <li><b>removable</b>: A flag indicating whether the role can be removed from the system.</li>
 * </ul>
 *
 * <p>The Role entity has a many-to-many relationship with the Permission entity.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "roles",
        indexes = {
                @Index(name = "idx_role_name", columnList = "name", unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_role_name", columnNames = "name")
        }
)
public class Role extends BaseEntity {

    /**
     * The unique name of the role. This is used for internal identification.
     * It is indexed and constrained to be unique in the database.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * A user-friendly name for the role, suitable for display in the UI.
     */
    @Column(name = "display_name", nullable = false)
    private String displayName;

    /**
     * A more detailed description of what the role entails.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Indicates whether the role can be removed from the system. Defaults to true.
     */
    @Column(name = "removable", nullable = false, columnDefinition = "boolean default true")
    private boolean removable;

    /**
     * A ManyToMany relationship with the Permission entity. This represents the permissions
     * granted to this role. Uses lazy fetching for performance optimization.
     * The `JoinTable` annotation defines the join table and the foreign key mappings.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions = new HashSet<>();

    /**
     * Adds a permission to the set of permissions associated with this role.
     * Also ensures the relationship is updated in the Permission entity.
     *
     * @param permission the permission to be added
     */
    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getRoles().add(this);
    }

    /**
     * Removes a permission from the set of permissions associated with this role.
     * Also ensures the relationship is updated in the Permission entity.
     *
     * @param permission the permission to be removed
     */
    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.getRoles().remove(this);
    }
}
