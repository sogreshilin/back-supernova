package ru.nsu.exception.http;

public class EventNotFoundException extends ResourceNotFoundException {
    public EventNotFoundException(long identifier) {
        super(identifier, ResourceType.EVENT);
    }

    public EventNotFoundException(String identifier) {
        super(identifier, ResourceType.EVENT);
    }
}
