package ru.nsu.controller.person.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.nsu.dto.ImageDto;
import ru.nsu.entity.enums.EventType;

@Value
public class EventDto {
    private final String title;

    private final Set<EventType> types;

    private final List<ImageDto> images;

    @JsonCreator
    public EventDto(
        @JsonProperty("title") String title,
        @JsonProperty("types") Set<EventType> types,
        @JsonProperty("images") List<ImageDto> images
    ) {
        this.title = title;
        this.types = types;
        this.images = images;
    }
}
