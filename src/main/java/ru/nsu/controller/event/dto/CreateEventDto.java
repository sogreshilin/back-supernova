package ru.nsu.controller.event.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private final Set<Long> imageIds;

    @JsonCreator
    public CreateEventDto(
        @JsonProperty("authorId") Long authorId,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("types") Set<EventType> types,
        @JsonProperty("interval") IntervalDto interval,
        @JsonProperty("location") LocationDto location,
        @JsonProperty("email") String email,
        @JsonProperty("siteUrl") String siteUrl,
        @JsonProperty("phone") String phone,
        @JsonProperty("imageIds") Set<Long> imageIds
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
        this.imageIds = imageIds;
    }
}
