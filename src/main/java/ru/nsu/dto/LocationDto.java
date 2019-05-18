package ru.nsu.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class LocationDto {
    private final Long id;

    @NotNull
    private final Double latitude;

    @NotNull
    private final Double longitude;

    private final String city;

    private final String address;

    @JsonCreator
    public LocationDto(
        @JsonProperty("id") Long id,
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("city") String city,
        @JsonProperty("address") String address
    ) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.address = address;
    }
}
