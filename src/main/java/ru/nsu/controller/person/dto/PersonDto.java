package ru.nsu.controller.person.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class PersonDto {
    private final String lastName;

    private final String firstName;

    private final Set<EventType> favourites;

    private List<EventDto> likedEvents;

    @JsonCreator
    public PersonDto(String lastName,
                     String firstName,
                     Set<EventType> favourites,
                     List<EventDto> likedEvents) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.favourites = favourites;
        this.likedEvents = likedEvents;
    }
}
