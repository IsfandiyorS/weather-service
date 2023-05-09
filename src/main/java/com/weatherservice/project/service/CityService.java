package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CityCreateDto;
import com.weatherservice.project.dto.CityDto;
import com.weatherservice.project.dto.CityUpdateDto;

import java.util.List;

public interface CityService {

    ResponseData<ResultMessage> createCity(final CityCreateDto cityCreateDto);

    ResponseData<ResultMessage> updateCity(final CityUpdateDto cityUpdateDto);

    ResponseData<CityDto> getById(final Long cityId);

    ResponseData<List<CityDto>> getAll();

    ResponseData<ResultMessage> deleteById(final Long cityId);

}
