package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.AttachRoleDto;
import com.weatherservice.project.dto.RoleCreateDto;

public interface RoleService {
    ResponseData<ResultMessage> attachRoleToUser(final AttachRoleDto attachRoleDto);

    ResponseData<ResultMessage> createRole(final RoleCreateDto roleCreateDto);

    // TODO: 02/05/23 create update and delete role
}
