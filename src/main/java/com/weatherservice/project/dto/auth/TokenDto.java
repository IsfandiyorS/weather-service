package com.weatherservice.project.dto.auth;

import com.weatherservice.project.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto implements GenericDto {
    private String token;
}
