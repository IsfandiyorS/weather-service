package com.weatherservice.project.dto;

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
public class CountryUpdateDto implements GenericCrudDto{

    @NotNull(message = "Country id must not be empty")
    private Long id;
    private String name;
    private String postalCode;
}
