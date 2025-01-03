package com.iambishal.spring_boot_rest_api_boilerplate.domain.permission;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.role.RoleDto;

import java.io.Serializable;
import java.util.Set;

/**
 * A Data Transfer Object (DTO) for the Permission entity.
 *
 * <p>The {@code PermissionDto} is used to encapsulate and transfer permission-related data
 * between different layers of the application, such as the service and controller layers.
 * It is a read-only, immutable representation of the Permission entity.</p>
 *
 * <p>Attributes of {@code PermissionDto}:</p>
 * <ul>
 *   <li><b>id</b>: The unique identifier of the permission.</li>
 *   <li><b>name</b>: The unique name of the permission.</li>
 *   <li><b>displayName</b>: The human-readable name of the permission.</li>
 *   <li><b>description</b>: A textual description of the permission.</li>
 *   <li><b>removable</b>: A flag indicating whether the permission can be deleted.</li>
 *   <li><b>roles</b>: A set of {@code RoleDto} representing the roles associated with the permission.</li>
 * </ul>
 *
 * <p>Implements {@code Serializable} to support serialization of permission data if needed.</p>
 */
public record PermissionDto(
        Long id,                         // The unique identifier of the permission
        String name,                     // The unique name of the permission
        String displayName,              // The human-readable name of the permission
        String description,              // A description of what the permission entails
        boolean removable,               // Flag indicating if the permission is removable
        Set<RoleDto> roles               // A set of roles associated with the permission
) implements Serializable {
}