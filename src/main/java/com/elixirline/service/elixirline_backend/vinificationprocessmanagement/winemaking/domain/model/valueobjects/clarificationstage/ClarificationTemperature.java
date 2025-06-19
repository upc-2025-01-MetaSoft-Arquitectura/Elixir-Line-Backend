package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ClarificationTemperature(Double clarificationTemperature) {
    public ClarificationTemperature {
        if (clarificationTemperature != null && clarificationTemperature < 0) {
            throw new IllegalArgumentException("Temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getClarificationTemperature() {
        return clarificationTemperature;
    }
}