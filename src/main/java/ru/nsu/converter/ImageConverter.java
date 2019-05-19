package ru.nsu.converter;

import ru.nsu.dto.ImageDto;
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
