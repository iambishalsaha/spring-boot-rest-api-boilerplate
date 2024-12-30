package com.iambishal.spring_boot_rest_api_boilerplate.permission;

import com.iambishal.spring_boot_rest_api_boilerplate.role.Role;
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
        name = "permissions",
        indexes = {
                @Index(name = "idx_permission_name", columnList = "name", unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_permission_name", columnNames = "name")
        }
)
public class Permission extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "removable", nullable = false, columnDefinition = "boolean default true")
    private boolean removable;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getPermissions().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getPermissions().remove(this);
    }
}
