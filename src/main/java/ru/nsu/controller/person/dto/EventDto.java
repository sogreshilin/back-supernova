package ru.nsu.controller.person.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class EventDto {
    private final String title;

    private final Set<EventType> types;

    @JsonCreator
    public EventDto(
        String title,
        Set<EventType> types
    ) {
        this.title = title;
        this.types = types;
    }
}
