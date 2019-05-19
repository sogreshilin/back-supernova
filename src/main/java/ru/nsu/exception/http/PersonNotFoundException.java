package ru.nsu.exception.http;

public class PersonNotFoundException extends ResourceNotFoundException {
    public PersonNotFoundException(long id) {
        super(id, ResourceType.PERSON);
    }

    public PersonNotFoundException(String vkId) {
        super(vkId, ResourceType.PERSON);
    }
}
