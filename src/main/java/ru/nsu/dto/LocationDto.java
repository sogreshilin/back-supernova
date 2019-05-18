package ru.nsu.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    public LocationDto(Long id, Double latitude, Double longitude, String city, String address) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.address = address;
    }
}
