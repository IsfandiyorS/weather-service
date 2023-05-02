package com.weatherservice.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FieldMessageException extends FieldValidationException{
    private final HttpStatus status;

    public FieldMessageException(String name,
                                 String message,
                                 HttpStatus status) {
        super(name, message);
        this.status = status;
    }

    public FieldMessageException(String name,
                                 String message) {
        super(name, message);
        this.status = HttpStatus.BAD_REQUEST;
    }

}
