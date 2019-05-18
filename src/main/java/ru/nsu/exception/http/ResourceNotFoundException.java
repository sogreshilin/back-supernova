package ru.nsu.exception.http;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.nsu.exception.SupernovaException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class ResourceNotFoundException extends SupernovaException {

    private final String identifier;

    private final ResourceType type;

    public ResourceNotFoundException(final String identifier, ResourceType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public ResourceNotFoundException(final long identifier, ResourceType type) {
        this.identifier = String.valueOf(identifier);
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return String.format("Failed to find [%s] with id [%s]", getType(), identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    @SuppressWarnings("checkstyle:NoWhitespaceBefore")
    public enum ResourceType {
        EVENT;

        @Override
        @JsonValue
        public String toString() {
            return super.toString();
        }
    }
}
