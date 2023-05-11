package com.weatherservice.project.mapper;


import com.weatherservice.project.dto.auth.UserCreateDto;
import com.weatherservice.project.dto.auth.UserDto;
import com.weatherservice.project.dto.auth.UserUpdateDto;
import com.weatherservice.project.model.User;
import com.weatherservice.project.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.weatherservice.project.utils.GenericValidationUtil.isFieldEmpty;

@Component
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<UserCreateDto, UserUpdateDto, UserDto, User> {

    private final PasswordEncoder passwordEncoder;
    @Override
    public User fromCreateDtoToEntity(UserCreateDto userCreateDto) {
        return User.builder()
                .email(userCreateDto.getEmail())
                .firstname(userCreateDto.getFirstname())
                .lastname(userCreateDto.getLastname())
                .age(userCreateDto.getAge())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .userType(UserType.USER)
                .build();
    }

    @Override
    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .age(user.getAge())
                .build();
    }

    @Override
    public User fromUpdateToEntity(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.getAge() != null && userUpdateDto.getAge() >= 0) {
            user.setAge(userUpdateDto.getAge());
        }
        if (isFieldEmpty(userUpdateDto.getEmail())) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (isFieldEmpty(userUpdateDto.getFirstname())) {
            user.setFirstname(userUpdateDto.getFirstname());
        }
        if (isFieldEmpty(userUpdateDto.getLastname())) {
            user.setLastname(userUpdateDto.getLastname());
        }
        if (isFieldEmpty(userUpdateDto.getPassword())) {
            user.setPassword(userUpdateDto.getPassword());
        }
        user.setLastModifiedDate(LocalDateTime.now());
        return user;
    }

}
