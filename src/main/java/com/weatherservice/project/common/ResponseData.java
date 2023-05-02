package com.weatherservice.project.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weatherservice.project.exception.CommonResponseException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("fieldValidationErrors")
    private List<FieldErrorMessage> fieldValidationErrors;

    @JsonProperty("timestamp")
    private long timestamp;

    public ResponseData(T data) {
        this.data = data;
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData(String errorMessage, List<FieldErrorMessage> validationErrors) {
        this.errorMessage = errorMessage;
        this.fieldValidationErrors = validationErrors;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData() {
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data) {
        return ResponseEntity.ok(new ResponseData<>(data));
    }

    public static ResponseEntity<Object> failedResponse(CommonResponseException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new ResponseData<>(null, exception.getMessage()));
    }

    public static ResponseEntity<Object> fieldValidationResponse(String errorMessage, List<FieldErrorMessage> fieldValidationErrors) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseData<>(errorMessage, fieldValidationErrors));
    }

}

