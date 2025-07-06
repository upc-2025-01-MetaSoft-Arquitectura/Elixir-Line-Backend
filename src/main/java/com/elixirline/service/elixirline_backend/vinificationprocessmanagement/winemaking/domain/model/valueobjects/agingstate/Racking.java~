package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Racking(Integer rackings) {
    public Racking {
        if (rackings != null && rackings < 0) {
            throw new IllegalArgumentException("Rackings cannot be less than zero.");
        }
    }

    @JsonValue
    public Integer getRackings() {
        return rackings;
    }
}