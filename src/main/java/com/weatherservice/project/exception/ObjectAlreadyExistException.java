package com.weatherservice.project.exception;

import org.springframework.http.HttpStatus;

public class ObjectAlreadyExistException extends CommonResponseException{
    public ObjectAlreadyExistException(String key) {
        super(key, HttpStatus.BAD_REQUEST);
    }
}
