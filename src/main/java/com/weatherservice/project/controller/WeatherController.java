package com.weatherservice.project.controller;

import com.weatherservice.project.common.ResponseData;
import com.weatherservice.project.common.ResultMessage;
import com.weatherservice.project.dto.WeatherCreateDto;
import com.weatherservice.project.dto.WeatherDto;
import com.weatherservice.project.dto.WeatherUpdateDto;
import com.weatherservice.project.exception.ProjectIntegrationException;
import com.weatherservice.project.service.WeatherService;
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
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
@Tag(name = "Weather control requests")
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(
            description = "Get weather information endpoint by id",
            summary = "This is a get weather information endpoint by its id",
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
    public ResponseEntity<ResponseData<WeatherDto>> getById(@PathVariable("id") Long weatherId) {
        return Optional.ofNullable(weatherId)
                .map(weatherService::getById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Get all subscribed weather information endpoint",
            summary = "This is a get all subscribed weather information endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "getSubscribedCityWeathers"
    )
    @GetMapping
    public ResponseEntity<ResponseData<List<WeatherDto>>> getSubscribedCityWeathers() {
        return Optional.of(weatherService.getSubscribedCityWeathers())
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Create weather information for city endpoint",
            summary = "This is a create weather information for city endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "createWeather"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseData<ResultMessage>> createWeather(@Valid @RequestBody WeatherCreateDto weatherCreateDto) {
        return Optional.ofNullable(weatherCreateDto)
                .map(weatherService::createWeather)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Update weather information endpoint",
            summary = "This is a update weather information endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "updateWeather"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseData<ResultMessage>> updateWeather(@Valid @RequestBody WeatherUpdateDto weatherUpdateDto) {
        return Optional.ofNullable(weatherUpdateDto)
                .map(weatherService::updateWeather)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

    @Operation(
            description = "Delete weather information endpoint by its id",
            summary = "This is a delete weather information endpoint by its id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            },
            method = "deleteWeather"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<ResultMessage>> deleteWeather(@PathVariable("id") Long weatherId) {
        return Optional.ofNullable(weatherId)
                .map(weatherService::deleteById)
                .map(ResponseEntity::ok)
                .orElseThrow(ProjectIntegrationException::new);
    }

}
