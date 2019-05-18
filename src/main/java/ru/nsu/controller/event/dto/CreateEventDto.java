package ru.nsu.controller.event.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import ru.nsu.dto.IntervalDto;
import ru.nsu.dto.LocationDto;
import ru.nsu.entity.enums.EventType;

@Value
public class CreateEventDto {
    @NotNull
    private final Long authorId;

    @NotNull
    private final String title;

    @NotNull
    private final String description;

    @NotEmpty
    private final Set<EventType> types;

    @Valid
    @NotNull
    private final IntervalDto interval;

    @Valid
    @NotNull
    private final LocationDto location;

    private final String email;

    private final String siteUrl;

    private final String phone;

    @JsonCreator
    public CreateEventDto(
        Long authorId,
        String title,
        String description,
        Set<EventType> types,
        IntervalDto interval,
        LocationDto location,
        String email,
        String siteUrl,
        String phone
    ) {
        this.authorId = authorId;
        this.title = title;
        this.description = description;
        this.types = types;
        this.interval = interval;
        this.location = location;
        this.email = email;
        this.siteUrl = siteUrl;
        this.phone = phone;
    }
}
