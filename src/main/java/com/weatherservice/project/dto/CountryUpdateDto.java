package com.weatherservice.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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
