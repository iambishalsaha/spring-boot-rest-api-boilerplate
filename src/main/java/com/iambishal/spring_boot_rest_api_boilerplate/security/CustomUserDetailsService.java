package com.iambishal.spring_boot_rest_api_boilerplate.security;

import com.iambishal.spring_boot_rest_api_boilerplate.domain.user.User;
import com.iambishal.spring_boot_rest_api_boilerplate.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class implements the UserDetailsService interface and is responsible for loading
 * user details from the database based on the provided username (which is actually the user's email in this case).
 * <p>
 * {@code @Service} annotation indicates that this class is a Spring service component.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // The UserRepository used to access user data from the database
    private final UserRepository userRepository;

    /**
     * Constructor that takes a UserRepository dependency.
     *
     * @param userRepository the UserRepository used to access user data
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is called by Spring Security to load user details. It takes the username (email) as input
     * and tries to find a matching user in the database using the UserRepository.
     *
     * <p>
     * If a user is found, it creates a UserPrincipal object and returns it.
     * If a user is not found, it throws a UsernameNotFoundException.
     *
     * @param email the email address of the user to load
     * @return a UserDetails object representing the loaded user
     * @throws UsernameNotFoundException if no user is found with the provided email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("We do not found any account associated with the email: " + email)
        );

        return new UserPrincipal(user);
    }
}
