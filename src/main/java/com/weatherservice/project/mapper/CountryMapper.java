package com.weatherservice.project.mapper;

import com.weatherservice.project.dto.CountryCreateDto;
import com.weatherservice.project.dto.CountryDto;
import com.weatherservice.project.dto.CountryUpdateDto;
import com.weatherservice.project.model.Country;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.weatherservice.project.utils.GenericValidationUtil.isFieldEmpty;

@Component
public class CountryMapper implements BaseMapper<CountryCreateDto, CountryUpdateDto, CountryDto, Country> {

    @Override
    public Country fromCreateDtoToEntity(CountryCreateDto createDto) {
        return Country.builder()
                .name(createDto.getName())
                .postalCode(createDto.getPostalCode())
                .build();
    }

    @Override
    public CountryDto entityToDto(Country entity) {
        return CountryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .postalCode(entity.getPostalCode())
                .build();
    }

    @Override
    public Country fromUpdateToEntity(CountryUpdateDto countryUpdateDto, Country country) {
        if (isFieldEmpty(countryUpdateDto.getName())) {
            country.setName(countryUpdateDto.getName());
        }
        if (isFieldEmpty(countryUpdateDto.getPostalCode())) {
            country.setPostalCode(countryUpdateDto.getPostalCode());
        }
        country.setLastModifiedDate(LocalDateTime.now());
        return country;
    }
}
