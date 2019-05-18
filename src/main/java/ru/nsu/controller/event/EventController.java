package ru.nsu.controller.event;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.entity.UploadedFile;
import ru.nsu.exception.http.FileProcessingException;
import ru.nsu.controller.event.dto.CreateEventDto;
import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.service.EventService;
import ru.nsu.service.FileService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final FileService fileService;

    @PostMapping
    public EventDto create(@RequestBody CreateEventDto event) {
        return eventService.create(event);
    }

    @GetMapping("/{eventId}")
    public EventDto findById(@PathVariable long eventId) {
        return eventService.findById(eventId);
    }

    @GetMapping("/by/{authorId}")
    public List<EventDto> findByAuthorId(@PathVariable long authorId) {
        return eventService.findByAuthorId(authorId);
    }

    @PostMapping("/{eventId}/images")
    public UploadedFile uploadImage(
        @PathVariable long eventId,
        @RequestParam(value = "file") MultipartFile file
    ) {
        try (InputStream is = file.getInputStream()) {
            UploadedFile uploadedFile = fileService.save(file.getOriginalFilename(), file.getContentType(), is);
            eventService.addImage(eventId, uploadedFile);
            return uploadedFile;
        } catch (IOException e) {
            throw new FileProcessingException("Error while uploading file with name=[" + file.getName() + "]");
        }
    }
}
