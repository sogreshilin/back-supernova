package ru.nsu.controller.person.converter;

import java.util.stream.Collectors;

import ru.nsu.controller.person.dto.EventDto;
import ru.nsu.converter.ImageConverter;
import ru.nsu.entity.Event;

public class EventConverter {

    public static EventDto toApi(Event event) {
        return new EventDto(
            event.getTitle(),
            event.getTypes(),
            event.getImages().stream().map(ImageConverter::toApi).collect(Collectors.toList())
        );
    }
}
