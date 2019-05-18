package ru.nsu.controller.person.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class PersonDto {
    private final Long id;

    private final String lastName;

    private final String firstName;

    private final Set<EventType> favouriteEventTypes;

    private final List<EventDto> favouriteEvents;

    private final List<EventDto> createdEvents;

    @JsonCreator
    public PersonDto(
        @JsonProperty("id") Long id,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("favouriteEventTypes") Set<EventType> favouriteEventTypes,
        @JsonProperty("favouriteEvents") List<EventDto> favouriteEvents,
        @JsonProperty("createdEvents") List<EventDto> createdEvents
    ) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.favouriteEventTypes = favouriteEventTypes;
        this.favouriteEvents = favouriteEvents;
        this.createdEvents = createdEvents;
    }
}
