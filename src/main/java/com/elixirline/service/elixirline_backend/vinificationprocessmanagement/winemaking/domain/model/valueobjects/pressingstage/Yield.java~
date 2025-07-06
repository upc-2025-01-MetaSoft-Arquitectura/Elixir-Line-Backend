package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Yield(
        @Column(name = "grape_yield_value")
        Double value
) {
    public Yield {
        if (value != null && value < 0) {
            throw new IllegalArgumentException("Yield cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getYield() {
        return value;
    }
}
