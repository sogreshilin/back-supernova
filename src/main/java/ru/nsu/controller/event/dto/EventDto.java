package ru.nsu.controller.event.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
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
        PersonDto author,
        String title,
        String description,
        Set<EventType> types,
        IntervalDto interval,
        LocationDto location,
        String email,
        String siteUrl,
        String phone,
        List<PersonDto> members,
        long likesCount,
        List<UploadedFile> images
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
