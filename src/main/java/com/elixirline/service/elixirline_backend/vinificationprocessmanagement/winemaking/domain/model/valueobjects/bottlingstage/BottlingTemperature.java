package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record BottlingTemperature(Double bottlingTemperature) {
    public BottlingTemperature {
        if (bottlingTemperature != null && bottlingTemperature < 0) {
            throw new IllegalArgumentException("Temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getBottlingTemperature() {
        return bottlingTemperature;
    }

    @JsonCreator
    public static BottlingTemperature from(Double bottlingTemperature) {
        return new BottlingTemperature(bottlingTemperature);
    }
}