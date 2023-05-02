package com.weatherservice.project.exception;

import lombok.Getter;

@Getter
public class FieldValidationException extends RuntimeException {
    private final String field;
    private final String message;

    public FieldValidationException(String field, String message) {
        super(message);
        this.field = field;
        this.message = message;
    }

}
