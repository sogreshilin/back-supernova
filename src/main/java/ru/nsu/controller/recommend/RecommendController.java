package ru.nsu.controller.recommend;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.entity.Event;
import ru.nsu.service.RecommendService;

@RestController
@RequestMapping("/{sessionId}/suggestions/{personId}")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping
    public List<EventDto> recommend(
        @PathVariable Long sessionId,
        @PathVariable Long personId
    ) {
        return recommendService.recommend(personId);
    }
}
