package com.weatherservice.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private Integer age;

    private String password;
}
