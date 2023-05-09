package com.weatherservice.project.service;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CountryCreateDto;
import com.weatherservice.project.dto.CountryDto;
import com.weatherservice.project.dto.CountryUpdateDto;

import java.util.List;

public interface CountryService {
    ResponseData<ResultMessage> createCountry(final CountryCreateDto countryCreateDto);

    ResponseData<ResultMessage> updateCountry(final CountryUpdateDto countryUpdateDto);

    ResponseData<CountryDto> getById(final Long countryId);

    ResponseData<List<CountryDto>> getAll();

    ResponseData<ResultMessage> deleteById(final Long countryId);

}
