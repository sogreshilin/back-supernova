package ru.nsu.controller.suggestion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.entity.Suggestion;
import ru.nsu.service.EventService;
import ru.nsu.service.RecommendService;
import ru.nsu.service.SuggestionService;

@RestController
@RequestMapping("/events/suggestions")
@RequiredArgsConstructor
public class SuggestionController {
    private final RecommendService recommendService;
    private final SuggestionService suggestionService;
    private final EventService eventService;

    @GetMapping("/{personId}")
    @Transactional
    public List<EventDto> firstSuggestions(@PathVariable Long personId,
                                           @RequestParam("sessionId") Long sessionId) {

        List<EventDto> eventDtoList = recommendService.recommend(personId);

        long min = Math.min(50, eventDtoList.size());

        if (eventDtoList.size() > min) {
            Suggestion suggestion = new Suggestion().setEventIds(
                eventDtoList.stream().map(EventDto::getId).toArray(Long[]::new))
                .setSessionId(sessionId).setSuggestionOffset(50L);

            suggestionService.saveSuggestion(suggestion);
        }

        return eventDtoList.subList(0, (int) min);
    }

    @GetMapping
    @Transactional
    public List<EventDto> firstSuggestions(@RequestParam("sessionId") Long sessionId) {

        Suggestion suggestion = suggestionService.getSuggestion(sessionId);
        List<Long> eventIds = Arrays.asList(suggestion.getEventIds());
        Long suggestionOffset = suggestion.getSuggestionOffset();

        List<EventDto> eventDtoList = eventService.findAllByIds(eventIds);

        long min = Math.min(50 + suggestionOffset, eventDtoList.size());

        if (eventDtoList.size() > min) {
            suggestion.setSuggestionOffset(suggestionOffset);
            suggestionService.saveSuggestion(suggestion);
        }

        return eventDtoList.subList(0, (int) min);
    }
}
