package com.weatherservice.project.dto.auth;

import com.weatherservice.project.dto.GenericCrudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.weatherservice.project.common.ResponseMessages.EMAIL_PATTERN_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.USER_FIRSTNAME_EMPTY_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.USER_LASTNAME_EMPTY_VALIDATION;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto implements GenericCrudDto {

    @NotNull
    @NotBlank(message = USER_FIRSTNAME_EMPTY_VALIDATION)
    private String firstname;

    @NotNull
    @NotBlank(message = USER_LASTNAME_EMPTY_VALIDATION)
    private String lastname;

    @Email(message = EMAIL_PATTERN_VALIDATION)
    private String email;

    private Integer age;

    private String password;
}
