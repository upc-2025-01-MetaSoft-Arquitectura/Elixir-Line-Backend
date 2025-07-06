package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FiltrationTemperature(Double filtrationTemperature) {
    public FiltrationTemperature {
        if (filtrationTemperature != null && filtrationTemperature < 0) {
            throw new IllegalArgumentException("Temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getFiltrationTemperature() {
        return filtrationTemperature;
    }

    @JsonCreator
    public static FiltrationTemperature from(Double filtrationTemperature) {
        return new FiltrationTemperature(filtrationTemperature);
    }
}