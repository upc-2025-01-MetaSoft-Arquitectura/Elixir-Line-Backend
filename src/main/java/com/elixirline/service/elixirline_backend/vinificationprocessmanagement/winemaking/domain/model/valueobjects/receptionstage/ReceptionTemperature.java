package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ReceptionTemperature(Double receptionTemperature) {
    public ReceptionTemperature {
        if (receptionTemperature != null && receptionTemperature < 0) {
            throw new IllegalArgumentException("Reception Temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getTemperature() {
        return receptionTemperature;
    }

    @JsonCreator
    public static ReceptionTemperature from(Double receptionTemperature) {
        return new ReceptionTemperature(receptionTemperature);
    }
}
