package com.iambishal.spring_boot_rest_api_boilerplate.role;

import com.iambishal.spring_boot_rest_api_boilerplate.permission.Permission;
import com.iambishal.spring_boot_rest_api_boilerplate.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "removable", nullable = false, columnDefinition = "boolean default true")
    private boolean removable;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions = new HashSet<>();

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getRoles().add(this);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.getRoles().remove(this);
    }
}
