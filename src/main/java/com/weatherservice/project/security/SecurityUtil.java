package com.weatherservice.project.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {
    public static Optional<SecurityUser> getCurrentUser() {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(user);
    }
}
