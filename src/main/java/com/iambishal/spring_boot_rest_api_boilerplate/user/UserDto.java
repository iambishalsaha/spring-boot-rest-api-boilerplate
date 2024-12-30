package com.iambishal.spring_boot_rest_api_boilerplate.user;

import com.iambishal.spring_boot_rest_api_boilerplate.role.RoleDto;

import java.io.Serializable;
import java.util.Set;

/**
 * A Data Transfer Object (DTO) for the User entity.
 *
 * <p>The {@code UserDto} is used to encapsulate and transfer user-related data
 * between different layers of the application, such as the service and controller layers.
 * It is a read-only, immutable representation of the User entity.</p>
 *
 * <p>Attributes of {@code UserDto}:</p>
 * <ul>
 *   <li><b>id</b>: The unique identifier of the user.</li>
 *   <li><b>firstName</b>: The first name of the user.</li>
 *   <li><b>lastName</b>: The last name of the user.</li>
 *   <li><b>email</b>: The user's unique email address.</li>
 *   <li><b>roles</b>: A set of {@code RoleDto} representing the roles assigned to the user.</li>
 * </ul>
 *
 * <p>Implements {@code Serializable} to support serialization of user data if needed.</p>
 */
public record UserDto(
        Long id,                         // The unique identifier of the user
        String firstName,                // The first name of the user
        String lastName,                 // The last name of the user
        String email,                    // The email address of the user
        Set<RoleDto> roles               // A set of roles associated with the user
) implements Serializable {
}