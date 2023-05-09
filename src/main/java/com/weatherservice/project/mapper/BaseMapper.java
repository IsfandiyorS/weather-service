package com.weatherservice.project.mapper;


import com.weatherservice.project.dto.GenericCrudDto;
import com.weatherservice.project.dto.GenericDto;
import com.weatherservice.project.model.Auditable;

public interface BaseMapper<C extends GenericCrudDto, U extends GenericCrudDto, D extends GenericDto, E extends Auditable> {
    E fromCreateDtoToEntity(C createDto);

    D entityToDto(E entity);

    E fromUpdateToEntity(U updateDto, E entity);

}
