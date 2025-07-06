package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Pressure(Double pressure) {
    public Pressure {
        if (pressure != null && pressure < 0) {
            throw new IllegalArgumentException("Pressure cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getPressure() {
        return pressure;
    }

    @JsonCreator
    public static Pressure from(Double pressure) {
        return new Pressure(pressure);
    }
}
