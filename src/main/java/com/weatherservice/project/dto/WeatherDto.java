package com.weatherservice.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto implements GenericDto{

    private Long id;

    private Long cityId;

    private Long wind;

    private Integer temperature;

    private Integer humidity;

    private Integer pressure;

}
