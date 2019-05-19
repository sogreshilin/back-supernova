package ru.nsu.service.integration.meetup.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GroupDto {
    private final String groupUserName;
    private final String timezone;

    public GroupDto(
        @JsonProperty("urlname") String groupUserName,
        @JsonProperty("timezone") String timezone
    ) {
        this.groupUserName = groupUserName;
        this.timezone = timezone;
    }
}
