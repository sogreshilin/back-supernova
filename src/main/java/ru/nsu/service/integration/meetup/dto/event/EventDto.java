package ru.nsu.service.integration.meetup.dto.event;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import lombok.Value;
import ru.nsu.dto.IntervalDto;
import ru.nsu.dto.LocationDto;

@Value
public class EventDto {
    private static final Map<String, ZoneOffset> ZONE_ID_TO_ZONE_OFFSET = ImmutableMap.of(
        "Europe/Moscow", ZoneOffset.ofHours(3),
        "Asia/Calcutta",ZoneOffset.ofHours(6),
        "Asia/Novosibirsk", ZoneOffset.ofHours(7)
    );

    private String externalId;

    private String title;

    private String description;

    private IntervalDto interval;

    private String groupUrlName;

    private LocationDto location;

    public EventDto(
        @JsonProperty("duration") Long duration,
        @JsonProperty("id") String externalId,
        @JsonProperty("name") String title,
        @JsonProperty("description") String description,
        @JsonProperty("local_date") LocalDate localDate,
        @JsonProperty("local_time") LocalTime localTime,
        @JsonProperty("venue") VenueDto venue,
        @JsonProperty("group") GroupDto group
    ) {
        this.externalId = externalId;
        this.title = title;
        this.description = description == null ? "" : description;
        if (venue != null) {
            this.location = new LocationDto(
                null,
                venue.getLatitude(),
                venue.getLongitude(),
                venue.getCity(),
                venue.getAddress()
            );
        } else {
            this.location = null;
        }
        this.groupUrlName = group.getGroupUserName();
        String timezone = group.getTimezone();
        Instant from = LocalDateTime.of(localDate, localTime)
            .toInstant(timezone == null ? ZoneOffset.ofHours(3) : ZONE_ID_TO_ZONE_OFFSET.get(timezone));
        Instant to = duration != null ? from.plus(duration, ChronoUnit.MILLIS) : null;
        this.interval = new IntervalDto(from, to);
    }
}
