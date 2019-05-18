package ru.nsu.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.exception.http.EventNotFoundException;
import ru.nsu.exception.http.PersonNotFoundException;
import ru.nsu.repository.EventRepository;
import ru.nsu.repository.PersonRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EventLikesService {
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    public boolean likeEventBy(long eventId, long personId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        List<Event> favouriteEvents = person.getFavouriteEvents();
        if (favouriteEvents.stream().map(Event::getId).anyMatch(id -> id == eventId)) {
            return false;
        }
        favouriteEvents.add(event);
        personRepository.save(person);
        return true;
    }

    public long getLikesCount(long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        return event.getLikedPersons().size();
    }
}
