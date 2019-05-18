package ru.nsu.exception.http;

public class PersonNotFoundException extends ResourceNotFoundException {
    public PersonNotFoundException(long id) {
        super(id, ResourceType.PERSON);
    }
}
