package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record DurationClarification(Integer durationClarification) {
    public DurationClarification {
        if (durationClarification != null && durationClarification <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero.");
        }
    }

    @JsonValue
    public Integer getDurationClarification() {
        return durationClarification;
    }
}