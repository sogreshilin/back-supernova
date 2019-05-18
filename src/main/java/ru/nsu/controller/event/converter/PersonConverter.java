package ru.nsu.controller.event.converter;

import java.util.List;
import java.util.stream.Collectors;

import ru.nsu.controller.event.dto.PersonDto;
import ru.nsu.entity.Person;

public class PersonConverter {
    public static PersonDto toApi(Person person) {
        return new PersonDto(
            person.getLastName(),
            person.getFirstName()
        );
    }

    public static List<PersonDto> toApi(List<Person> persons) {
        return persons.stream().map(PersonConverter::toApi).collect(Collectors.toList());
    }
}
