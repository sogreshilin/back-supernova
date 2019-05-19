package ru.nsu.service.integration.meetup.dto.event;

import java.util.List;

import lombok.Value;

@Value
public class FindEventsResponseDto {
    private final List<EventDto> events;
}
