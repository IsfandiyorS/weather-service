package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.WeatherCreateDto;
import com.weatherservice.project.dto.WeatherDto;
import com.weatherservice.project.dto.WeatherUpdateDto;

import java.util.List;

public interface WeatherService {

    ResponseData<ResultMessage> createWeather(final WeatherCreateDto weatherCreateDto);

    ResponseData<ResultMessage> updateWeather(final WeatherUpdateDto weatherUpdateDto);

    ResponseData<WeatherDto> getById(final Long weatherId);

    ResponseData<List<WeatherDto>> getSubscribedCityWeathers();

    ResponseData<ResultMessage> deleteById(final Long weatherId);
}
