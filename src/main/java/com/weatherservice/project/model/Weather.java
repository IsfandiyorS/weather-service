package com.weatherservice.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Weather weather = (Weather) o;
        return Objects.equals(wind, weather.wind) && Objects.equals(temperature, weather.temperature) && Objects.equals(pressure, weather.pressure) && Objects.equals(humidity, weather.humidity) && Objects.equals(city, weather.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wind, temperature, pressure, humidity, city);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "wind=" + wind +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", city=" + city +
                ", id=" + id +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
