package ru.nsu.service.integration.meetup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.nsu.controller.event.dto.CreateEventDto;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.entity.UploadedFile;
import ru.nsu.exception.http.FileProcessingException;
import ru.nsu.repository.EventRepository;
import ru.nsu.service.EventService;
import ru.nsu.service.FileService;
import ru.nsu.service.PersonService;
import ru.nsu.service.integration.meetup.dto.event.EventDto;
import ru.nsu.service.integration.meetup.dto.group.GetGroupResponseDto;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MeetUpIntegrationService {
    private final RestTemplate restTemplate;
    private final PersonService personService;
    private final EventService eventService;
    private final EventRepository eventRepository;
    private final FileService fileService;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final List<String> queryParamsList = Arrays.asList(
        "?lat=55.751244&lon=37.618423&radius=100", // Moscow
        "?lat=56.833332&lon=60.583332&radius=100", // Yekaterinburg
        "?lat=59.937500&lon=30.308611&radius=100", // St Petersburg
        "?lat=55.018803&lon=82.933952&radius=100" // Novosibirsk
    );

    public void trigger() {
        executor.execute(this::synchronize);
    }

    private void synchronize() {
        List<Object> externalIds = eventRepository.findAll().stream()
            .map(Event::getExternalId)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        for (String queryParams: queryParamsList) {
            ResponseEntity<List<EventDto>> eventsResponseEntity = restTemplate.exchange(
                "https://api.meetup.com/find/events" + queryParams,
                HttpMethod.GET,
                createRequestEntity(),
                new ParameterizedTypeReference<List<EventDto>>() {
                }
            );

            List<EventDto> events = eventsResponseEntity.getBody();
            if (events == null) {
                return;
            }

            for (EventDto event : events) {
                try {
                    if (externalIds.contains(event.getExternalId())) {
                        continue;
                    }

                    ResponseEntity<GetGroupResponseDto> groupResponseEntity = restTemplate.exchange(
                        "https://api.meetup.com/" + event.getGroupUrlName(),
                        HttpMethod.GET,
                        createRequestEntity(),
                        GetGroupResponseDto.class
                    );

                    GetGroupResponseDto group = groupResponseEntity.getBody();

                    Person person = personService.create(
                        new Person()
                            .setFirstName(Objects.requireNonNull(group).getOrganizer().getFirstName())
                            .setLastName(group.getOrganizer().getLastName())
                    );

                    try {
                        UploadedFile uploadedFile = fileService.uploadFile(new URL(group.getImageUrl()));

                        eventService.create(
                            new CreateEventDto(
                                event.getExternalId(),
                                person.getId(),
                                event.getTitle(),
                                event.getDescription(),
                                group.getType() == null ? null : Collections.singleton(group.getType()),
                                event.getInterval(),
                                event.getLocation(),
                                null,
                                null,
                                null,
                                Collections.singleton(uploadedFile.getId())
                            )
                        );
                    } catch (MalformedURLException e) {
                        throw new FileProcessingException("Could not resolve URL=[" + group.getImageUrl() + "]");
                    }
                } catch (Exception e) {
                    log.info("Event with externalId=[{}] was not processed", event.getExternalId());
                }
            }
        }
    }

    private HttpEntity createRequestEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer dffc2ccb6597577b89ea1d19d34804a9");
        return new HttpEntity<>(httpHeaders);
    }
}
