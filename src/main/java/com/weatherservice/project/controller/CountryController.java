package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.CountryCreateDto;
import com.weatherservice.project.dto.CountryDto;
import com.weatherservice.project.dto.CountryUpdateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
@Tag(name = "Country requests")
public class CountryController {
    private final CountryService countryService;

    @Operation(
            description = "Get country endpoint by id",
            summary = "This is a get country endpoint by its id",
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
    public ResponseEntity<ResponseData<CountryDto>> getById(@PathVariable("id") Long countryId) {
        return Optional.ofNullable(countryId)
                .map(countryService::getById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Get all countries endpoint",
            summary = "This is a get all countries endpoint",
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
    public ResponseEntity<ResponseData<List<CountryDto>>> getAll() {
        return Optional.of(countryService.getAll())
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Create country endpoint",
            summary = "This is a create country endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "createCountry"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseData<ResultMessage>> createCountry(@Valid @RequestBody CountryCreateDto countryUpdateDto) {
        return Optional.ofNullable(countryUpdateDto)
                .map(countryService::createCountry)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Update country endpoint",
            summary = "This is a update country endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "updateCountry"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseData<ResultMessage>> updateCountry(@Valid @RequestBody CountryUpdateDto countryUpdateDto) {
        return Optional.ofNullable(countryUpdateDto)
                .map(countryService::updateCountry)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Delete country endpoint by its id",
            summary = "This is a delete country endpoint by its id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "deleteCountryById"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<ResultMessage>> deleteCountryById(@PathVariable("id") Long countryId) {
        return Optional.ofNullable(countryId)
                .map(countryService::deleteById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }
}
