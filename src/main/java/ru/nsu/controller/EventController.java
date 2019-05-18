package ru.nsu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.entity.Event;
import ru.nsu.service.EventService;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping("/eventId")
    public Event findById(@RequestParam long eventId) {
        return eventService.findById(eventId);
    }
}
