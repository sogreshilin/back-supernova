package ru.nsu.service.integration.meetup.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PhotoDto {
    private final String url;

    public PhotoDto(@JsonProperty("highres_link") String url) {
        this.url = url;
    }
}
