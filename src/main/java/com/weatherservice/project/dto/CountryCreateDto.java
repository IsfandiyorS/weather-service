package com.weatherservice.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCreateDto implements GenericCrudDto {
    @NotNull
    @NotEmpty(message = "Country name must not be empty")
    private String name;

    @NotNull
    @NotEmpty(message = "Country postal code must not be empty")
    private String postalCode;
}
