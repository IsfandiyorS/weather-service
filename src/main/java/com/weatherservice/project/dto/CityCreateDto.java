package com.weatherservice.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateDto implements GenericCrudDto {

    @NotNull
    @NotBlank(message = "City name must not be empty.")
    private String name;

    @NotNull
    @NotBlank(message = "City postal code must not be empty.")
    private String postalCode;

    @NotNull(message = "Country id must not be empty.")
    private Long countryId;
}
