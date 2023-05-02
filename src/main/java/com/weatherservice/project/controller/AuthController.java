package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.LoginDto;
import com.weatherservice.project.dto.TokenDto;
import com.weatherservice.project.dto.UserCreateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<ResultMessage>> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return Optional.ofNullable(userCreateDto)
                .map(authService::registerUser)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<TokenDto>> login(@Valid @RequestBody LoginDto loginDto){
        return Optional.ofNullable(loginDto)
                .map(authService::login)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

}
