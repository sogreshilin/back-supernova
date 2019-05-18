package ru.nsu.controller.person.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class EventDto {
    private final String title;

    private final Set<EventType> types;

    @JsonCreator
    public EventDto(
        @JsonProperty("title") String title,
        @JsonProperty("types") Set<EventType> types
    ) {
        this.title = title;
        this.types = types;
    }
}
