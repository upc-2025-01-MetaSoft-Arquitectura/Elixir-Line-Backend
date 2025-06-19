package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PressPressure(Double pressPressure) {
    public PressPressure {
        if (pressPressure != null && pressPressure < 0) {
            throw new IllegalArgumentException("Press pressure cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getPressPressure() {
        return pressPressure;
    }
}
