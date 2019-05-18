package ru.nsu.converter;

import org.springframework.stereotype.Component;
import ru.nsu.dto.IntervalDto;
import ru.nsu.entity.embeddable.Interval;

@Component
public class IntervalConverter {
    public IntervalDto toApi(Interval interval) {
        return new IntervalDto(interval.getFrom(), interval.getTo());
    }

    public Interval fromApi(IntervalDto interval) {
        return new Interval().setFrom(interval.getFrom()).setTo(interval.getTo());
    }
}
