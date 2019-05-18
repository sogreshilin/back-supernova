package ru.nsu.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class IntervalDto {
    @NotNull
    private final Instant from;

    private final Instant to;

    @JsonCreator
    public IntervalDto(
        @JsonProperty("from") Instant from,
        @JsonProperty("to") Instant to) {
        this.from = from;
        this.to = to;
    }
}
