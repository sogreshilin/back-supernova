package ru.nsu.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.controller.event.CreateEventDto;
import ru.nsu.converter.IntervalConverter;
import ru.nsu.converter.LocationConverter;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.exception.http.EventNotFoundException;
import ru.nsu.exception.http.PersonNotFoundException;
import ru.nsu.repository.EventRepository;
import ru.nsu.repository.PersonRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final IntervalConverter intervalConverter;
    private final LocationConverter locationConverter;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    public Event create(CreateEventDto eventDto) {
        long authorId = eventDto.getAuthorId();
        Person author = personRepository.findById(authorId).orElseThrow(() -> new PersonNotFoundException(authorId));
        return eventRepository.save(new Event()
            .setTitle(eventDto.getTitle())
            .setDescription(eventDto.getDescription())
            .setInterval(intervalConverter.fromApi(eventDto.getInterval()))
            .setSiteUrl(eventDto.getSiteUrl())
            .setEmail(eventDto.getEmail())
            .setPhone(eventDto.getPhone())
            .setAuthor(author)
            .setLocation(locationConverter.fromApi(eventDto.getLocation()))
            .setTypes(eventDto.getTypes())
        );
    }

    public Event findById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
    }

    public List<Event> findByAuthorId(long authorId) {
        personRepository.findById(authorId).orElseThrow(() -> new PersonNotFoundException(authorId));
        return eventRepository.findAllByAuthorId(authorId);
    }
}
