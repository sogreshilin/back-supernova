package ru.nsu.controller.event;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.entity.Event;
import ru.nsu.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public Event create(@Valid @RequestBody CreateEventDto event) {
        return eventService.create(event);
    }

    @GetMapping("/{eventId}")
    public Event findById(@PathVariable long eventId) {
        return eventService.findById(eventId);
    }
}
