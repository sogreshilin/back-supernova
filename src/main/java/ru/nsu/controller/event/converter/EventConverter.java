package ru.nsu.controller.event.converter;

import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.converter.IntervalConverter;
import ru.nsu.converter.LocationConverter;
import ru.nsu.entity.Event;

public class EventConverter {

    public static EventDto toApi(Event event) {
        return new EventDto(
            PersonConverter.toApi(event.getAuthor()),
            event.getTitle(),
            event.getDescription(),
            event.getTypes(),
            IntervalConverter.toApi(event.getInterval()),
            LocationConverter.toApi(event.getLocation()),
            event.getEmail(),
            event.getSiteUrl(),
            event.getPhone(),
            PersonConverter.toApi(event.getMembers())
        );
    }
}
