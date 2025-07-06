package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record QuantityKg(Double quantityKg) {
    public QuantityKg {
        if (quantityKg != null && quantityKg < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getQuantityKg() {
        return quantityKg;
    }

    @JsonCreator
    public static QuantityKg from(Double quantityKg) {
        return new QuantityKg(quantityKg);
    }
}
