package com.weatherservice.project.mapper;

import com.weatherservice.project.dto.WeatherCreateDto;
import com.weatherservice.project.dto.WeatherDto;
import com.weatherservice.project.dto.WeatherUpdateDto;
import com.weatherservice.project.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.weatherservice.project.utils.GenericValidationUtil.isObjectEmpty;

@Component
public class WeatherMapper implements BaseMapper<WeatherCreateDto, WeatherUpdateDto, WeatherDto, Weather> {
    @Override
    public Weather fromCreateDtoToEntity(WeatherCreateDto createDto) {
        return Weather.builder()
                .wind(createDto.getWind())
                .pressure(createDto.getPressure())
                .humidity(createDto.getHumidity())
                .temperature(createDto.getTemperature())
                .build();
    }

    @Override
    public WeatherDto entityToDto(Weather entity) {
        return WeatherDto.builder()
                .id(entity.getId())
                .cityId(entity.getCity().getId())
                .humidity(entity.getHumidity())
                .pressure(entity.getPressure())
                .temperature(entity.getTemperature())
                .wind(entity.getWind())
                .build();
    }

    @Override
    public Weather fromUpdateToEntity(WeatherUpdateDto updateDto, Weather entity) {

        if (isObjectEmpty(updateDto.getHumidity())) {
            entity.setHumidity(updateDto.getHumidity());
        }
        if (isObjectEmpty(updateDto.getWind())) {
            entity.setWind(updateDto.getWind());
        }
        if (isObjectEmpty(updateDto.getTemperature())) {
            entity.setTemperature(updateDto.getTemperature());
        }
        if (isObjectEmpty(updateDto.getPressure())) {
            entity.setPressure(updateDto.getPressure());
        }
        entity.setLastModifiedDate(LocalDateTime.now());
        return entity;
    }
}
