package ru.nsu.entity.embeddable;

import java.time.Instant;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Embeddable
public class Interval {
    private Instant from;
    private Instant to;
}
