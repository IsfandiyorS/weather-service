package com.weatherservice.project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectIntegrationException extends RuntimeException{

    private final String message;

    private final Integer code;

    public ProjectIntegrationException() {
        this.message = "Cloud Gantt is not available";
        this.code = 503;
    }

}
