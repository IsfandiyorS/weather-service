package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CityCreateDto;
import com.weatherservice.project.dto.CityDto;
import com.weatherservice.project.dto.CityUpdateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Tag(name = "City requests")
public class CityController {

    private final CityService cityService;

    @Operation(
            description = "Get city endpoint by id",
            summary = "This is a get city endpoint by its id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "getById"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ResponseData<CityDto>> getById(@PathVariable("id") Long cityId) {
        return Optional.ofNullable(cityId)
                .map(cityService::getById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Get all cities endpoint",
            summary = "This is a get all cities endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "getAll"
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ResponseData<List<CityDto>>> getAll() {
        return Optional.of(cityService.getAll())
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Create city endpoint",
            summary = "This is a create city endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "createCity"
    )
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData<ResultMessage>> createCity(@Valid @RequestBody CityCreateDto cityCreateDto) {
        return Optional.ofNullable(cityCreateDto)
                .map(cityService::createCity)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Update city endpoint",
            summary = "This is a update city endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "updateCity"
    )
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData<ResultMessage>> updateCity(@Valid @RequestBody CityUpdateDto cityUpdateDto) {
        return Optional.ofNullable(cityUpdateDto)
                .map(cityService::updateCity)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Delete city endpoint by its id",
            summary = "This is a delete city endpoint by its id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "deleteCityById"
    )
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData<ResultMessage>> deleteCityById(@PathVariable("id") Long cityId) {
        return Optional.ofNullable(cityId)
                .map(cityService::deleteById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }
}
