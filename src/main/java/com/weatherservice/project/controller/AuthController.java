package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.auth.LoginDto;
import com.weatherservice.project.dto.auth.TokenDto;
import com.weatherservice.project.dto.auth.UserCreateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Tag(name = "Authentication requests")
public class AuthController {

    private final AuthService authService;

    @Operation(
            description = "Registration endpoint for user",
            summary = "This is a registration get endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal server error", responseCode = "500")
            },
            method = "registerUser"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseData<ResultMessage>> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return Optional.ofNullable(userCreateDto)
                .map(authService::registerUser)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Login endpoint for user",
            summary = "This is a login post endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal server error", responseCode = "500")
            },
            method = "login"
    )
    @PostMapping("/login")
    public ResponseEntity<ResponseData<TokenDto>> login(@Valid @RequestBody LoginDto loginDto) {
        return Optional.ofNullable(loginDto)
                .map(authService::login)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

}
