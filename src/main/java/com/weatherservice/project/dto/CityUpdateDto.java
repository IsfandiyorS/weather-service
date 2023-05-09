package com.weatherservice.project.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityUpdateDto implements GenericCrudDto {

    @NotNull(message = "City id must not be empty.")
    private Long id;

    private String name;

    private String postalCode;

    @NotNull(message = "Country id must not be empty.")
    private Long countryId;

}
