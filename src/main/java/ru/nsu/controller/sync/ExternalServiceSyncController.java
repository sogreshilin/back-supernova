package ru.nsu.controller.sync;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.service.integration.meetup.MeetUpIntegrationService;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class ExternalServiceSyncController {
    private final MeetUpIntegrationService meetUpIntegrationService;

    @PostMapping("/meetup")
    @EventListener(ApplicationReadyEvent.class)
    public ResponseEntity<String> triggerMeetUpSync() {
        meetUpIntegrationService.trigger();
        return new ResponseEntity<>("Meetup synchronization triggered", HttpStatus.OK);
    }
}
