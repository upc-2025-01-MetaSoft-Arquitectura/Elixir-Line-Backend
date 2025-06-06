package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record InitialGrapeQuantityKg(Double initialGrapeQuantityKg) {
    public InitialGrapeQuantityKg {
        if (initialGrapeQuantityKg <= 0) {
            throw new IllegalArgumentException("InitialGrapeQuantityKg must be positive");
        }
    }

    @JsonValue
    public Double getInitialGrapeQuantityKg() {
        return initialGrapeQuantityKg;
    }
}
