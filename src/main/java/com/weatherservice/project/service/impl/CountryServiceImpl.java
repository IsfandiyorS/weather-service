package com.weatherservice.project.service.impl;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CountryCreateDto;
import com.weatherservice.project.dto.CountryDto;
import com.weatherservice.project.dto.CountryUpdateDto;
import com.weatherservice.project.exception.ObjectNotFoundException;
import com.weatherservice.project.mapper.CountryMapper;
import com.weatherservice.project.model.Country;
import com.weatherservice.project.repository.CountryRepository;
import com.weatherservice.project.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.weatherservice.project.common.FieldNames.ID;
import static com.weatherservice.project.common.ResponseMessages.COUNTRY_SUCCESSFULLY_CREATED;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_NOT_FOUND_BY_FIELD;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_DELETED;
import static com.weatherservice.project.common.ResponseMessages.OBJECT_SUCCESSFULLY_UPDATED;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public ResponseData<ResultMessage> createCountry(CountryCreateDto countryCreateDto) {

        return Optional.of(countryCreateDto)
                .map(countryMapper::fromCreateDtoToEntity)
                .map(countryRepository::save)
                .map(country ->
                        ResponseData.<ResultMessage>builder()
                                .data(
                                        ResultMessage.builder()
                                                .message(COUNTRY_SUCCESSFULLY_CREATED)
                                                .build()
                                )
                                .build()
                ).get();
    }

    @Override
    public ResponseData<ResultMessage> updateCountry(CountryUpdateDto countryUpdateDto) {
        return countryRepository.findById(countryUpdateDto.getId())
                .map(country -> countryMapper.fromUpdateToEntity(countryUpdateDto, country))
                .map(countryRepository::save)
                .map(country -> ResponseData.<ResultMessage>builder()
                        .data(
                                ResultMessage.builder()
                                        .message(format(OBJECT_SUCCESSFULLY_UPDATED, "Country"))
                                        .build()
                        )
                        .build())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Country", ID)));
    }

    @Override
    public ResponseData<CountryDto> getById(Long countryId) {
        return countryRepository.findById(countryId)
                .map(countryMapper::entityToDto)
                .map(countryDto -> ResponseData.<CountryDto>builder()
                        .data(countryDto)
                        .build())
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Country", ID)));
    }

    @Override
    public ResponseData<List<CountryDto>> getAll() {
        return ResponseData.<List<CountryDto>>builder()
                .data(
                        countryRepository.findAll()
                                .stream()
                                .map(countryMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ResponseData<ResultMessage> deleteById(Long countryId) {

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ObjectNotFoundException(format(OBJECT_NOT_FOUND_BY_FIELD, "Country", ID)));

        countryRepository.delete(country);
        return ResponseData.<ResultMessage>builder()
                .data(
                        ResultMessage.builder()
                                .message(format(OBJECT_SUCCESSFULLY_DELETED, "Country", ID))
                                .build()
                )
                .build();
    }
}
