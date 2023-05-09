package com.weatherservice.project.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldErrorMessage {

    @JsonProperty("name")
    private String name;

    @JsonProperty("errors")
    private String errorMessage;

    public FieldErrorMessage(String name, String errorMessage) {
        this.name = name;
        this.errorMessage = errorMessage;
    }
}
