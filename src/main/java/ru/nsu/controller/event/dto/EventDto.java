package ru.nsu.controller.event.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.nsu.controller.event.converter.ImageConverter;
import ru.nsu.dto.IntervalDto;
import ru.nsu.dto.LocationDto;
import ru.nsu.entity.UploadedFile;
import ru.nsu.entity.enums.EventType;

@Value
public class EventDto {
    private final PersonDto author;

    private final String title;

    private final String description;

    private final Set<EventType> types;

    private final IntervalDto interval;

    private final LocationDto location;

    private final String email;

    private final String siteUrl;

    private final String phone;

    private final List<PersonDto> members;

    private final long likesCount;

    private final List<ImageDto> images;

    @JsonCreator
    public EventDto(
        @JsonProperty("author") PersonDto author,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("types") Set<EventType> types,
        @JsonProperty("interval") IntervalDto interval,
        @JsonProperty("location") LocationDto location,
        @JsonProperty("email") String email,
        @JsonProperty("siteUrl") String siteUrl,
        @JsonProperty("phone") String phone,
        @JsonProperty("members") List<PersonDto> members,
        @JsonProperty("likesCount") long likesCount,
        @JsonProperty("images") List<UploadedFile> images
    ) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.types = types;
        this.interval = interval;
        this.location = location;
        this.email = email;
        this.siteUrl = siteUrl;
        this.phone = phone;
        this.members = members;
        this.likesCount = likesCount;
        this.images = images.stream().map(ImageConverter::toApi).collect(Collectors.toList());
    }
}
