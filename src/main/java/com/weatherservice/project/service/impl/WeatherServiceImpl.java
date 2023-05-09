package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.WeatherCreateDto;
import com.weatherservice.project.dto.WeatherDto;
import com.weatherservice.project.dto.WeatherUpdateDto;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.mapper.WeatherMapper;
import com.weatherservice.project.model.City;
import com.weatherservice.project.model.Weather;
import com.weatherservice.project.repository.CityRepository;
import com.weatherservice.project.repository.WeatherRepository;
import com.weatherservice.project.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_NOT_FOUND_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_CREATED;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_UPDATED;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final CityRepository cityRepository;

    @Override
    public ResponseData<ResultMessage> createWeather(WeatherCreateDto weatherCreateDto) {

        City city = cityRepository.findById(weatherCreateDto.getCityId())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "City", ID)));

        return Optional.of(weatherMapper.fromCreateDtoToEntity(weatherCreateDto))
                .map(weather -> {
                    weather.setCity(city);
                    weatherRepository.save(weather);
                    return weather;
                })
                .map(response ->
                        ResponseData.<ResultMessage>builder()
                                .data(
                                        ResultMessage.builder()
                                                .message(format(OBJECT_SUCCESSFULLY_CREATED, "Weather"))
                                                .build()
                                )
                                .build()
                ).get();
    }

    @Override
    public ResponseData<ResultMessage> updateWeather(WeatherUpdateDto weatherUpdateDto) {
        return weatherRepository.findWeatherByCityIdAndId(weatherUpdateDto.getCityId(), weatherUpdateDto.getId())
                .map(weather -> weatherMapper.fromUpdateToEntity(weatherUpdateDto, weather))
                .map(weatherRepository::save)
                .map(response ->
                        ResponseData.<ResultMessage>builder()
                                .data(
                                        ResultMessage.builder()
                                                .message(format(OBJECT_SUCCESSFULLY_UPDATED, "Weather"))
                                                .build()
                                )
                                .build()
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Weather", ID)));
    }

    @Override
    public ResponseData<WeatherDto> getById(Long weatherId) {
        return weatherRepository.findById(weatherId)
                .map(weatherMapper::entityToDto)
                .map(weatherDto ->
                        ResponseData.<WeatherDto>builder()
                                .data(weatherDto)
                                .build()
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Weather", ID)));
    }

    @Override
    public ResponseData<List<WeatherDto>> getSubscribedCityWeathers() {
        // TODO: 06/05/23 get user by Context holder until getting from it i am using -1L as user id
        List<WeatherDto> weatherDtoList = weatherRepository.findWeatherByUserSubscriptions(1L)
                .stream()
                .map(weatherMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseData.<List<WeatherDto>>builder().data(weatherDtoList).build();
    }

    @Override
    public ResponseData<ResultMessage> deleteById(Long weatherId) {

        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Weather", ID)));
        weatherRepository.delete(weather);
        return ResponseData.<ResultMessage>builder()
                .data(
                        ResultMessage.builder()
                                .message("Weather successfully deleted")
                                .build())
                .build();
    }
}
