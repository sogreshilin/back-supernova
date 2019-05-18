package ru.nsu.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class IntervalDto {
    @NotNull
    private final Instant from;

    private final Instant to;

    @JsonCreator
    public IntervalDto(Instant from, Instant to) {
        this.from = from;
        this.to = to;
    }
}
