package com.weatherservice.project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.UserDto;
import com.weatherservice.project.dto.UserUpdateDto;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.mapper.UserMapper;
import com.weatherservice.project.model.User;
import com.weatherservice.project.repository.UserRepository;
import com.weatherservice.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.ResponseMessages.USER_DELETED;
import static com.weatherservice.project.common.ResponseMessages.USER_DOES_NOT_EXIST_BY_FIELD;
import static com.weatherservice.project.mapper.UserMapper.mapToUserDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseData<List<UserDto>> getAllUsers() {
        List<User> userList = userRepository.findAll();

        var userDtoList = userList.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());

        return new ResponseData<>(userDtoList);
    }

    @Override
    public ResponseData<ResultMessage> updateUser(UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));

        return new ResponseData<>(ResultMessage.builder().message("There is no any logic").build());
        // TODO: 01/05/23 make some logic use ObjectMapper library
    }

    @Override
    public ResponseData<UserDto> getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));
        return new ResponseData<>(mapToUserDto(user));
    }

    @Override
    public ResponseData<ResultMessage> deleteUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));

        userRepository.delete(user);
        var resultMessage = ResultMessage.builder()
                .message(USER_DELETED)
                .build();

        return new ResponseData<>(resultMessage);
    }

}
