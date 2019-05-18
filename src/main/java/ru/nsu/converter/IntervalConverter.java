package ru.nsu.converter;

import ru.nsu.dto.IntervalDto;
import ru.nsu.entity.embeddable.Interval;

public class IntervalConverter {
    public static IntervalDto toApi(Interval interval) {
        return new IntervalDto(interval.getFrom(), interval.getTo());
    }

    public static Interval fromApi(IntervalDto interval) {
        return new Interval().setFrom(interval.getFrom()).setTo(interval.getTo());
    }
}
