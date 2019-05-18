package ru.nsu.controller.event.converter;

import ru.nsu.controller.event.dto.ImageDto;
import ru.nsu.entity.UploadedFile;

public class ImageConverter {

    public static ImageDto toApi(UploadedFile source) {
        return new ImageDto(
            source.getId(),
            source.getName(),
            source.getName(),
            source.getCreated()
        );
    }
}
