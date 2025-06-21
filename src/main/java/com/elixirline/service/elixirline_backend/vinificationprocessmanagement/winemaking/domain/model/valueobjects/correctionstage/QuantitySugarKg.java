package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record QuantitySugarKg(Double quantitySugarKg) {
    public QuantitySugarKg {
        if (quantitySugarKg != null && quantitySugarKg < 0) {
            throw new IllegalArgumentException("Quantity Sugar cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getQuantitySugarKg() {
        return quantitySugarKg;
    }
}
