package com.weatherservice.project.mapper;

import com.weatherservice.project.dto.CityCreateDto;
import com.weatherservice.project.dto.CityDto;
import com.weatherservice.project.dto.CityUpdateDto;
import com.weatherservice.project.model.City;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.weatherservice.project.utils.GenericValidationUtil.isFieldEmpty;

@Component
public class CityMapper implements BaseMapper<CityCreateDto, CityUpdateDto, CityDto, City> {

    @Override
    public City fromCreateDtoToEntity(CityCreateDto createDto) {
        return City.builder()
                .name(createDto.getName())
                .postalCode(createDto.getPostalCode())
                .build();
    }

    @Override
    public CityDto entityToDto(City entity) {
        return CityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .postalCode(entity.getPostalCode())
                .countryId(entity.getCountry().getId())
                .build();
    }

    @Override
    public City fromUpdateToEntity(CityUpdateDto cityUpdateDto, City city) {
        if (isFieldEmpty(cityUpdateDto.getName())) {
            city.setName(cityUpdateDto.getName());
        }
        if (isFieldEmpty(cityUpdateDto.getPostalCode())) {
            city.setPostalCode(cityUpdateDto.getPostalCode());
        }
        city.setLastModifiedDate(LocalDateTime.now());
        return city;
    }
}
