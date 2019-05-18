package ru.nsu.controller.person.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class FavouriteDto {
    private final EventType type;

    @JsonCreator
    public FavouriteDto(EventType type) {
        this.type = type;
    }
}
