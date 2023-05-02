package com.weatherservice.project.mapper;


import com.weatherservice.project.dto.UserCreateDto;
import com.weatherservice.project.dto.UserDto;
import com.weatherservice.project.model.User;
import com.weatherservice.project.type.UserRole;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .age(user.getAge())
                .build();
    }

    // TODO: 01/05/23 decode password
    public static User mapToEntity(UserCreateDto userCreateDto) {
        return User.builder()
                .email(userCreateDto.getEmail())
                .firstname(userCreateDto.getFirstname())
                .lastname(userCreateDto.getLastname())
                .age(userCreateDto.getAge())
                .password(userCreateDto.getPassword())
                .userRole(UserRole.USER)
                .build();
    }
}
