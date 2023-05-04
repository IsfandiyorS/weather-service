package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.AttachRoleDto;
import com.weatherservice.project.dto.RoleCreateDto;
import com.weatherservice.project.exception.ObjectAlreadyExistException;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.model.Role;
import com.weatherservice.project.model.User;
import com.weatherservice.project.repository.RoleRepository;
import com.weatherservice.project.repository.UserRepository;
import com.weatherservice.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_NOT_FOUND_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.USER_DOES_NOT_EXIST_BY_FIELD;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseData<ResultMessage> attachRoleToUser(AttachRoleDto attachRoleDto) {

        User user = userRepository.findById(attachRoleDto.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));

        //// TODO: 02/05/23 create method for all of them to avoid duplication
        Role role = roleRepository.findById(attachRoleDto.getRoleId())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Role", ID)));

        Set<Role> roleSet = new HashSet<>();
        if (role != null) {
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        userRepository.save(user);
        return new ResponseData<>(ResultMessage.builder().message("Role attached").build());
    }

    @Override
    public ResponseData<ResultMessage> createRole(RoleCreateDto roleCreateDto) {

        Optional<Role> optionalRole = roleRepository.findByCode(roleCreateDto.getCode());

        if (optionalRole.isPresent()) {
            throw new ObjectAlreadyExistException("Role already exists");
        }

        var role = Role.builder()
                .code(roleCreateDto.getCode())
                .name(roleCreateDto.getName())
                .build();
        roleRepository.save(role);
        return new ResponseData<>(ResultMessage.builder().message("Role created").build());
    }
}
