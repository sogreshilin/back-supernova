package ru.nsu.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.controller.event.converter.EventConverter;
import ru.nsu.controller.event.dto.CreateEventDto;
import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.converter.IntervalConverter;
import ru.nsu.converter.LocationConverter;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.entity.UploadedFile;
import ru.nsu.exception.http.EventNotFoundException;
import ru.nsu.exception.http.PersonNotFoundException;
import ru.nsu.repository.EventRepository;
import ru.nsu.repository.FileRepository;
import ru.nsu.repository.PersonRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final FileRepository fileRepository;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    public EventDto create(CreateEventDto eventDto) {
        long authorId = eventDto.getAuthorId();
        Person author = personRepository.findById(authorId).orElseThrow(() -> new PersonNotFoundException(authorId));
        List<UploadedFile> images = fileRepository.findAllById(eventDto.getImageIds());
        return EventConverter.toApi(
            eventRepository.save(new Event()
                .setExternalId(eventDto.getExternalId())
                .setTitle(eventDto.getTitle())
                .setDescription(eventDto.getDescription())
                .setInterval(IntervalConverter.fromApi(eventDto.getInterval()))
                .setSiteUrl(eventDto.getSiteUrl())
                .setEmail(eventDto.getEmail())
                .setPhone(eventDto.getPhone())
                .setAuthor(author)
                .setLocation(LocationConverter.fromApi(eventDto.getLocation()))
                .setTypes(eventDto.getTypes())
                .setImages(images)
            )
        );
    }

    public EventDto findById(long eventId) {
        return EventConverter.toApi(
            eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId))
        );
    }

    public List<EventDto> findAllByIds(List<Long> eventIds) {
        return eventRepository.findAllById(eventIds).stream().map(EventConverter::toApi).collect(Collectors.toList());
    }

    public List<EventDto> findByAuthorId(long authorId) {
        personRepository.findById(authorId).orElseThrow(() -> new PersonNotFoundException(authorId));
        return eventRepository.findAllByAuthorId(authorId).stream().map(EventConverter::toApi).collect(Collectors.toList());
    }

    public void addImage(long eventId, UploadedFile image) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        event.getImages().add(image);
        eventRepository.save(event);
    }

    public List<EventDto> findFavouriteEventsByPersonId(long userId) {
        Person person = personRepository.findById(userId).orElseThrow(() -> new PersonNotFoundException(userId));
        return person.getFavouriteEvents().stream().map(EventConverter::toApi).collect(Collectors.toList());
    }
}
