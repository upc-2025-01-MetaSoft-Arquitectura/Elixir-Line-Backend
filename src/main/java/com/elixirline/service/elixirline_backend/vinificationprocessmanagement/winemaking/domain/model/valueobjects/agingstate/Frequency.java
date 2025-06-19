package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Frequency(Integer frequency) {
    public Frequency {
        if (frequency != null && frequency < 0) {
            throw new IllegalArgumentException("Frequency cannot be less than zero.");
        }
    }

    @JsonValue
    public Integer getFrequency() {
        return frequency;
    }
}