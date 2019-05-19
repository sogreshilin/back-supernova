package ru.nsu.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.controller.event.converter.EventConverter;
import ru.nsu.controller.event.dto.EventDto;
import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.recommender.TransformedData;
import ru.nsu.recommender.Recommender;
import ru.nsu.repository.EventRepository;
import ru.nsu.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    public List<Person> getPersonList() {
        return personRepository.findAll();
    }

    public List<EventDto> recommend(Long id) {
        TransformedData transformedData = new TransformedData(getPersonList());
        Recommender recommender = new Recommender(transformedData, eventRepository.findAll());
        recommender.train();
        Map<Event, Double> predictions = recommender.predict(id);

        Map<Event, Double> result = predictions.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(
                Comparator.reverseOrder()
            ))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result.keySet().stream().map(EventConverter::toApi).collect(Collectors.toList());
    }
}
