package ru.nsu.controller.person.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class FavouriteDto {
    private final EventType type;

    @JsonCreator
    public FavouriteDto(@JsonProperty("type") EventType type) {
        this.type = type;
    }
}
