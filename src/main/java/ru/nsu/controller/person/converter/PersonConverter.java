package ru.nsu.controller.person.converter;

import java.util.List;
import java.util.stream.Collectors;

import ru.nsu.controller.person.dto.EventDto;
import ru.nsu.controller.person.dto.PersonDto;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;

public class PersonConverter {

    public static PersonDto toApi(Person person) {
        return new PersonDto(
            person.getLastName(),
            person.getFirstName(),
            person.getFavourites(),
            toApi(person.getLikedEvents())
        );
    }

    public static List<EventDto> toApi(List<Event> events) {
        return events.stream().map(EventConverter::toApi).collect(Collectors.toList());
    }
}
