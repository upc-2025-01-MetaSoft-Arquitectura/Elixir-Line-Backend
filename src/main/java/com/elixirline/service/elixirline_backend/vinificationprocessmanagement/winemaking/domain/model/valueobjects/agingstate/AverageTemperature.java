package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AverageTemperature(Double averageTemperature) {
    public AverageTemperature {
        if (averageTemperature != null && averageTemperature < 0) {
            throw new IllegalArgumentException("Average temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getAverageTemperature() {
        return averageTemperature;
    }
}
