package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FilledBottles(Integer fillCount) {
    public FilledBottles {
        if (fillCount != null && fillCount < 0) {
            throw new IllegalArgumentException("Filled bottles cannot be less than zero.");
        }
    }

    @JsonValue
    public Integer getFillCount() {
        return fillCount;
    }
}
