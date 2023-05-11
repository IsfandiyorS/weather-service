package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CityCreateDto;
import com.weatherservice.project.dto.CityDto;
import com.weatherservice.project.dto.CityUpdateDto;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.mapper.CityMapper;
import com.weatherservice.project.model.City;
import com.weatherservice.project.model.Country;
import com.weatherservice.project.repository.CityRepository;
import com.weatherservice.project.repository.CountryRepository;
import com.weatherservice.project.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.FieldNames.CITY;
import static com.weatherservice.project.common.FieldNames.COUNTRY;
import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_NOT_FOUND_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_CREATED;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_DELETED;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    @Override
    public ResponseData<ResultMessage> createCity(CityCreateDto cityCreateDto) {
        Country county = countryRepository.findById(cityCreateDto.getCountryId())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, COUNTRY, ID)));
        return Optional.of(cityCreateDto)
                .map(cityDto -> {
                    City city = cityMapper.fromCreateDtoToEntity(cityDto);
                    city.setCountry(county);
                    return city;
                })
                .map(cityRepository::save)
                .map(response ->
                        ResponseData.<ResultMessage>builder()
                                .data(
                                        ResultMessage.builder()
                                                .message(format(OBJECT_SUCCESSFULLY_CREATED, CITY))
                                                .build()
                                )
                                .build()
                ).get();
    }

    @Override
    public ResponseData<ResultMessage> updateCity(CityUpdateDto cityUpdateDto) {
        Country county = countryRepository.findById(cityUpdateDto.getCountryId())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, COUNTRY, ID)));

        return cityRepository.findById(cityUpdateDto.getId())
                .map(city -> {
                    cityMapper.fromUpdateToEntity(cityUpdateDto, city);
                    city.setCountry(county);
                    return city;
                })
                .map(cityRepository::save)
                .map(response -> ResponseData.<ResultMessage>builder()
                        .data(
                                ResultMessage.builder()
                                        .message("City successfully updated.")
                                        .build()
                        )
                        .build()
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, CITY, ID)));
    }

    @Override
    public ResponseData<CityDto> getById(Long cityId) {
        return cityRepository.findById(cityId)
                .map(cityMapper::entityToDto)
                .map(cityDto -> ResponseData.<CityDto>builder()
                        .data(cityDto)
                        .build()
                )
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, CITY, ID)));
    }

    @Override
    public ResponseData<List<CityDto>> getAll() {
        return ResponseData.<List<CityDto>>builder()
                .data(
                        cityRepository.findAll()
                                .stream()
                                .map(cityMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ResponseData<ResultMessage> deleteById(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, CITY, ID)));

        cityRepository.delete(city);
        return ResponseData.<ResultMessage>builder()
                .data(
                        ResultMessage.builder()
                                .message(format(OBJECT_SUCCESSFULLY_DELETED, CITY, ID))
                                .build()
                )
                .build();
    }
}
