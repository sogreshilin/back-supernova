package ru.nsu.controller.event.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class PersonDto {
    private final String lastName;

    private final String firstName;

    @JsonCreator
    public PersonDto(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
