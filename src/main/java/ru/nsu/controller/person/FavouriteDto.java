package ru.nsu.controller.person;

import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class FavouriteDto {
    private final EventType type;
}
