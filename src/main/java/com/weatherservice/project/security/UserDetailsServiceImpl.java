package com.weatherservice.project.security;

import com.weatherservice.project.model.User;
import com.weatherservice.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this email not found."));
        log.info("UserDetailsServiceImpl User {}", user);
        return SecurityUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .locked(user.isLocked())
                .enabled(user.isEnabled())
                .userType(user.getUserType())
                .build();
    }

    public Optional<SecurityUser> findSecurityUser(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user ->
                        SecurityUser.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .password(user.getPassword())
                                .userType(user.getUserType())
                                .enabled(user.isEnabled())
                                .locked(user.isLocked())
                                .build()
                );


    }

}
