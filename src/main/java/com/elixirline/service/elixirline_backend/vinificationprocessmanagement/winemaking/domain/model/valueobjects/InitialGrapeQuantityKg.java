package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record InitialGrapeQuantityKg(double quantity) {
    public InitialGrapeQuantityKg {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Initial grape quantity must be greater than zero");
        }
    }

    @JsonValue
    public double getInitialGrapeQuantityKg() {
        return quantity;
    }
}

