package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.AttachRoleDto;
import com.weatherservice.project.dto.RoleCreateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<ResultMessage>> create(@RequestBody RoleCreateDto roleCreateDto) {
        return Optional.ofNullable(roleCreateDto)
                .map(roleService::createRole)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }


    @PostMapping("/attach-role")
    public ResponseEntity<ResponseData<ResultMessage>> create(@RequestBody AttachRoleDto attachRoleDto) {
        return Optional.ofNullable(attachRoleDto)
                .map(roleService::attachRoleToUser)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }
}
