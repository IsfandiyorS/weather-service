package com.weatherservice.project.dto.auth;

import com.weatherservice.project.dto.GenericCrudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto implements GenericCrudDto {

    @NotNull(message = "Id must not be empty")
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private Integer age;

    private String password;
}
