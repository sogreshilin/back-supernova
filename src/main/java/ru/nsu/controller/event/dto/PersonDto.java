package ru.nsu.controller.event.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PersonDto {
    private final String lastName;

    private final String firstName;

    @JsonCreator
    public PersonDto(
        @JsonProperty("lastName") String lastName,
        @JsonProperty("firstName") String firstName
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
