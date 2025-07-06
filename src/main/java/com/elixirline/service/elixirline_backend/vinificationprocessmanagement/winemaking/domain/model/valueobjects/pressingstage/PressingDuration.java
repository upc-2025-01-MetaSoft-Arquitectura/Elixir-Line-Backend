package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PressingDuration(Integer duration) {
    public PressingDuration {
        if (duration != null && duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero.");
        }
    }

    @JsonValue
    public Integer getDuration() {
        return duration;
    }

    @JsonCreator
    public static PressingDuration from(Integer duration) {
        return new PressingDuration(duration);
    }
}
