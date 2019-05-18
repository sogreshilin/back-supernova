package ru.nsu.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.service.RecommendService;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping("/{personId}/{eventId}")
    public double addFavourite(@PathVariable long personId, @PathVariable long eventId) {
        return recommendService.recommend(personId, eventId);
    }
}
