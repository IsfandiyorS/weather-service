package com.weatherservice.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherservice.project.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.weatherservice.project.common.ResponseMessages.AUTHORIZATION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter()
                .write(objectMapper.writeValueAsString(ResponseEntity
                .status(UNAUTHORIZED)
                .body(new ResponseData<>(null, AUTHORIZATION_FAILED))));
    }
}