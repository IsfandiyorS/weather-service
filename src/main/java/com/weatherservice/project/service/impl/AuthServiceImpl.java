package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.auth.LoginDto;
import com.weatherservice.project.dto.auth.TokenDto;
import com.weatherservice.project.dto.auth.UserCreateDto;
import com.weatherservice.project.exception.FieldMessageException;
import com.weatherservice.project.mapper.UserMapper;
import com.weatherservice.project.model.User;
import com.weatherservice.project.repository.UserRepository;
import com.weatherservice.project.service.AuthService;
import com.weatherservice.project.utils.UserValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.weatherservice.project.common.FieldNames.EMAIL;
import static com.weatherservice.project.common.FieldNames.PASSWORD;
import static com.weatherservice.project.common.ResponseMessages.EMAIL_EXIST;
import static com.weatherservice.project.common.ResponseMessages.ENTER_VALID_PASSWORD;
import static com.weatherservice.project.common.ResponseMessages.USER_DOES_NOT_EXIST_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.USER_SUCCESSFULLY_CREATED;
import static com.weatherservice.project.utils.UserValidationUtils.validateEmail;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public ResponseData<ResultMessage> registerUser(UserCreateDto userCreateDto) {
        validateRequest(userCreateDto);

        return Optional.of(userCreateDto)
                .map(userMapper::fromCreateDtoToEntity)
                .map(userRepository::save)
                .map(user ->
                        ResponseData.<ResultMessage>builder()
                                .data(
                                        ResultMessage.builder()
                                                .message(USER_SUCCESSFULLY_CREATED)
                                                .build()
                                )
                                .build()
                ).get();

//        User user = UserMapper.mapToEntity(userCreateDto);
//        userRepository.save(user);
//        var resultMessage = ResultMessage.builder()
//                .message(USER_SUCCESSFULLY_CREATED)
//                .build();
//        return new ResponseData<>(resultMessage);
    }

    @Override
    public ResponseData<TokenDto> login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        validateEmail(email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new FieldMessageException(EMAIL, format(USER_DOES_NOT_EXIST_BY_FIELD, EMAIL), NOT_FOUND));

        boolean passwordNotMatch = !Objects.equals(loginDto.getPassword(), user.getPassword());
//        boolean passwordNotMatch = !passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        UserValidationUtils.checkCondition(passwordNotMatch, new FieldMessageException(PASSWORD, ENTER_VALID_PASSWORD));

        // TODO: 01/05/23 generate jwt token
//        String token = jwtTokenProvider.generateToken(user);
        return new ResponseData<>(new TokenDto("token"));
    }

    private void validateRequest(UserCreateDto userCreateDto) {
        validateEmail(userCreateDto.getEmail());
        UserValidationUtils.validatePassword(userCreateDto.getPassword());

        UserValidationUtils.checkCondition(
                userRepository.findByEmail(
                                userCreateDto.getEmail())
                        .isPresent(),
                new FieldMessageException(EMAIL, EMAIL_EXIST));
    }


}
