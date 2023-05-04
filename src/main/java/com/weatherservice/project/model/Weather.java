package com.weatherservice.project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather extends Auditable {

    private Long wind;

    private Integer temperature;

    private Integer pressure;

    private Integer humidity;

    @ManyToOne
    private City city;
}
