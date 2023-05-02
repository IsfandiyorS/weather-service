package com.weatherservice.project.dto;

import com.weatherservice.project.common.ResponseMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.weatherservice.project.common.ResponseMessages.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

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
