package ru.nsu.converter;

import ru.nsu.dto.LocationDto;
import ru.nsu.entity.Location;

public class LocationConverter {
    public static Location fromApi(LocationDto source) {
        if (source == null) {
            return null;
        }
        return new Location()
            .setId(source.getId())
            .setLatitude(source.getLatitude())
            .setLongitude(source.getLongitude())
            .setCity(source.getCity())
            .setAddress(source.getAddress());
    }

    public static LocationDto toApi(Location source) {
        if (source == null) {
            return null;
        }
        return new LocationDto(
            source.getId(),
            source.getLatitude(),
            source.getLongitude(),
            source.getCity(),
            source.getAddress()
        );
    }
}
