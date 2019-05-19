package ru.nsu.service.integration.meetup.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CategoryDto {
    private final String eventType;

    public CategoryDto(@JsonProperty("name") String eventType) {
        this.eventType = eventType;
    }
}
