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
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCreateDto implements GenericCrudDto {

    @NotNull(message = "Weather wind must not be empty.")
    private Long wind;

    @NotNull(message = "Weather temperature must not be empty.")
    private Integer temperature;

    @NotNull(message = "Weather pressure must not be empty.")
    private Integer pressure;

    @NotNull(message = "Weather humidity must not be empty.")
    private Integer humidity;

    @NotNull(message = "City id must not be empty.")
    private Long cityId;
}
