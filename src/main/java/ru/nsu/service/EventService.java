package ru.nsu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.entity.Event;
import ru.nsu.exception.http.EventNotFoundException;
import ru.nsu.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
    }
}
