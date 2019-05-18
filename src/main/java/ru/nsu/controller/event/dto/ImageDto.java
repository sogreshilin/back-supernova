package ru.nsu.controller.event.dto;

import java.time.Instant;

import lombok.Value;

@Value
public class ImageDto {
    private final long id;
    private final String name;
    private final String contentType;
    private final Instant created;
}
