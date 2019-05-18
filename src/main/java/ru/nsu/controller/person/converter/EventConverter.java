package ru.nsu.controller.person.converter;

import ru.nsu.controller.person.dto.EventDto;
import ru.nsu.entity.Event;

public class EventConverter {

    public static EventDto toApi(Event event) {
        return new EventDto(
            event.getTitle(),
            event.getTypes()
        );
    }
}
