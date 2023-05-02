package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.UserDto;
import com.weatherservice.project.dto.UserUpdateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseData<List<UserDto>>> getAllUsers() {
        return Optional.of(userService.getAllUsers())
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<ResultMessage>> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return Optional.ofNullable(userUpdateDto)
                .map(userService::updateUser)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UserDto>> getUserById(@PathVariable("id") Long id) {
        return Optional.ofNullable(id)
                .map(userService::getUserById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<ResultMessage>> deleteUserById(@PathVariable("id") Long id) {
        return Optional.ofNullable(id)
                .map(userService::deleteUserById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }
}
