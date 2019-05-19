package ru.nsu.service.integration.meetup.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class OrganizerDto {
    private final String firstName;
    private final String lastName;

    public OrganizerDto(@JsonProperty("name") String name) {
        String[] nameParts = name.split(" ");
        this.firstName = nameParts[0];
        this.lastName = nameParts.length > 1 ? nameParts[1] : "";
    }
}
