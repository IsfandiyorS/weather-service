package com.weatherservice.project.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonResponseException{
    public BadRequestException(String key) {
        super(key, HttpStatus.BAD_REQUEST);
    }

}
