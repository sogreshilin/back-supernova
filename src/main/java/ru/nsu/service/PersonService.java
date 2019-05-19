package ru.nsu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.controller.person.converter.PersonConverter;
import ru.nsu.controller.person.dto.PersonDto;
import ru.nsu.entity.Person;
import ru.nsu.entity.enums.EventType;
import ru.nsu.exception.http.PersonNotFoundException;
import ru.nsu.repository.PersonRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public PersonDto getById(long personId) {
        return PersonConverter.toApi(personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId)));
    }

    public PersonDto getByVkId(String vkId) {
        return PersonConverter.toApi(personRepository.findByVkId(vkId).orElseThrow(() -> new PersonNotFoundException(vkId)));
    }

    public PersonDto add(long personId, EventType type) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        person.getFavouriteEventTypes().add(type);
        return PersonConverter.toApi(personRepository.save(person));
    }
}
