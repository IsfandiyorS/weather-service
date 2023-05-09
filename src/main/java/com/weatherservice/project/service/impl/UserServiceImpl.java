package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.auth.UserDto;
import com.weatherservice.project.dto.auth.UserUpdateDto;
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
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseData<List<UserDto>> getAllUsers() {
        return ResponseData.<List<UserDto>>builder()
                .data(
                        userRepository.findAll()
                                .stream()
                                .map(userMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ResponseData<ResultMessage> updateUser(UserUpdateDto userUpdateDto) {

        return userRepository.findById(userUpdateDto.getId())
                .map(user -> userMapper.fromUpdateToEntity(userUpdateDto, user))
                .map(user ->
                        {
                            userRepository.save(user);
                            return ResponseData.<ResultMessage>builder()
                                    .data(
                                            ResultMessage.builder()
                                                    .message("User data successfully updated.")
                                                    .build()
                                    )
                                    .build();
                        }
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));
    }

    @Override
    public ResponseData<UserDto> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::entityToDto)
                .map(user ->
                        ResponseData.<UserDto>builder()
                                .data(user)
                                .build()
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));
    }

    @Override
    public ResponseData<ResultMessage> deleteUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(format(USER_DOES_NOT_EXIST_BY_FIELD, ID)));

        userRepository.delete(user);

        return ResponseData.<ResultMessage>builder()
                .data(
                        ResultMessage
                                .builder()
                                .message(USER_DELETED)
                                .build()
                )
                .build();
    }

}
