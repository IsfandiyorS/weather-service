package com.weatherservice.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto implements GenericDto {
    private Long id;

    private String name;

    private String postalCode;

    private Long countryId;

}
