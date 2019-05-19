package ru.nsu.service.integration.meetup.dto.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class VenueDto {
    private final Double latitude;

    private final Double longitude;

    private final String address;

    private final String city;

    @JsonCreator
    public VenueDto(
        @JsonProperty("lat") Double lat,
        @JsonProperty("lon") Double longitude,
        @JsonProperty("address_1") String address,
        @JsonProperty("city") String city
    ) {
        this.latitude = lat;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
    }
}
