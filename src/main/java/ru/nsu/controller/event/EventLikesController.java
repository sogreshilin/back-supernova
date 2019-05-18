package ru.nsu.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.service.EventLikesService;

@RestController
@RequestMapping("/events/{eventId}")
@RequiredArgsConstructor
public class EventLikesController {
    private final EventLikesService eventLikesService;

    @PostMapping("/likes/{personId}")
    public ResponseEntity likeEventBy(@PathVariable long eventId, @PathVariable long personId) {
        boolean newLikeAdded = eventLikesService.likeEventBy(eventId, personId);
        return new ResponseEntity(newLikeAdded ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED);
    }

    @GetMapping("/likes")
    public long getLikesCount(@PathVariable long eventId) {
        return eventLikesService.getLikesCount(eventId);
    }
}
