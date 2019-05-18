package ru.nsu.converter;

import org.springframework.stereotype.Component;
import ru.nsu.dto.LocationDto;
import ru.nsu.entity.Location;

@Component
public class LocationConverter {
    public Location fromApi(LocationDto source) {
        return new Location()
            .setId(source.getId())
            .setLatitude(source.getLatitude())
            .setLongitude(source.getLongitude())
            .setCity(source.getCity())
            .setAddress(source.getAddress());
    }

    public LocationDto toApi(Location source) {
        return new LocationDto(
            source.getId(),
            source.getLatitude(),
            source.getLongitude(),
            source.getCity(),
            source.getAddress()
        );
    }
}
