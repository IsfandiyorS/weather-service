package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.LoginDto;
import com.weatherservice.project.dto.TokenDto;
import com.weatherservice.project.dto.UserCreateDto;

public interface AuthService {
    ResponseData<ResultMessage> registerUser(final UserCreateDto userCreateDto);

    ResponseData<TokenDto> login(final LoginDto loginDto);
}
