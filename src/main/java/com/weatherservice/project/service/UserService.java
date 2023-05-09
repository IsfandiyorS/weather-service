package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.auth.UserDto;
import com.weatherservice.project.dto.auth.UserUpdateDto;

import java.util.List;

public interface UserService {

    ResponseData<List<UserDto>> getAllUsers();

    ResponseData<ResultMessage> updateUser(final UserUpdateDto userUpdateDto);

    ResponseData<UserDto> getUserById(final Long userId);

    ResponseData<ResultMessage> deleteUserById(final Long userId);

    // TODO: 01/05/23 create find by username method

    // TODO: 01/05/23 get all users subscribes

}
