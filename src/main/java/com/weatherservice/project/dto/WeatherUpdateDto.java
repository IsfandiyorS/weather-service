package com.weatherservice.project.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherUpdateDto implements GenericCrudDto {

    @NotNull(message = "Weather id must not be empty.")
    private Long id;

    @NotNull(message = "Weather city id must not be empty.")
    private Long cityId;

    private Long wind;

    private Integer temperature;

    private Integer pressure;

    private Integer humidity;
}
