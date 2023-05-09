package com.weatherservice.project.dto.auth;

import com.weatherservice.project.dto.GenericDto;
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
public class LoginDto implements GenericDto {

    private String email;

    private String password;

}
