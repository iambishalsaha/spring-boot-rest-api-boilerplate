package com.iambishal.spring_boot_rest_api_boilerplate.security;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.role.Role;
import com.iambishal.spring_boot_rest_api_boilerplate.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class implements the UserDetails interface and represents a security principal
 * that is used by Spring Security. It wraps a User entity and provides the authorities
 * (roles) to Spring Security.
 */
public class UserPrincipal implements UserDetails {

    // The User entity instance to be wrapped and adapted for Spring Security
    private final User user;

    /**
     * Constructor to initialize UserPrincipal with a User entity.
     *
     * @param user The User entity containing user details
     */
    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * This method returns the authorities (roles) of the user. It iterates over the user's
     * roles and converts them to SimpleGrantedAuthority objects, which Spring Security understands.
     *
     * @return a collection of GrantedAuthority objects representing the user's roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    /**
     * This method returns the user's password.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * This method returns the user's email, which is used as the username for authentication.
     *
     * @return the user's email
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // Other required methods from UserDetails interface
    // (e.g. isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled etc.)
    // can be implemented here with logic based on the User entity or other security requirements.
}
