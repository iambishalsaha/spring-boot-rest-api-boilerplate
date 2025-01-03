package com.iambishal.spring_boot_rest_api_boilerplate.domain.role;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.permission.PermissionDto;

import java.io.Serializable;
import java.util.Set;

/**
 * A Data Transfer Object (DTO) for the Role entity.
 *
 * <p>The {@code RoleDto} is used to encapsulate and transfer role-related data
 * between different layers of the application, such as the service and controller layers.
 * It is a read-only, immutable representation of the Role entity.</p>
 *
 * <p>Attributes of {@code RoleDto}:</p>
 * <ul>
 *   <li><b>id</b>: The unique identifier of the role.</li>
 *   <li><b>name</b>: The unique name of the role.</li>
 *   <li><b>displayName</b>: The human-readable name of the role.</li>
 *   <li><b>description</b>: A textual description of the role.</li>
 *   <li><b>removable</b>: A flag indicating whether the role can be deleted.</li>
 *   <li><b>permissions</b>: A set of {@code PermissionDto} representing the permissions associated with the role.</li>
 * </ul>
 *
 * <p>Implements {@code Serializable} to support serialization of role data if needed.</p>
 */
public record RoleDto(
        Long id,                         // The unique identifier of the role
        String name,                     // The unique name of the role
        String displayName,              // The human-readable name of the role
        String description,              // A description of the role's purpose
        boolean removable,               // Flag indicating if the role is removable
        Set<PermissionDto> permissions   // A set of permissions associated with the role
) implements Serializable {
}