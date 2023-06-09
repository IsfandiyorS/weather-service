package com.weatherservice.project.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends CommonResponseException{
    public ObjectNotFoundException(String key) {
        super(key, HttpStatus.NOT_FOUND);
    }
}
