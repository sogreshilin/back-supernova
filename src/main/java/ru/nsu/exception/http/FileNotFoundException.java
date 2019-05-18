package ru.nsu.exception.http;

public class FileNotFoundException extends ResourceNotFoundException {
    public FileNotFoundException(long identifier) {
        super(identifier, ResourceType.EVENT);
    }

    public FileNotFoundException(String identifier) {
        super(identifier, ResourceType.EVENT);
    }
}
