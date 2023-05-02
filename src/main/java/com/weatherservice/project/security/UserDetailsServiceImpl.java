package com.weatherservice.project.security;

import com.weatherservice.project.exception.CommonResponseException;
import com.weatherservice.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.weatherservice.project.common.ResponseMessages.USER_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CommonResponseException(
                        USER_DOES_NOT_EXIST,
                        HttpStatus.UNAUTHORIZED));

        return SecurityUser.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .enabled(user.isEnabled())
                .locked(user.isLocked())
                .build();
    }

    public Optional<SecurityUser> findSecurityUser(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(user ->
                        SecurityUser.builder()
                                .userId(user.getId())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .userRole(user.getUserRole())
                                .enabled(user.isEnabled())
                                .locked(user.isLocked())
                                .build()
                );


    }
}
