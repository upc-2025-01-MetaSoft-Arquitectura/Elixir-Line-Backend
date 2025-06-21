package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record GrapeVariety(String variety) {
    public GrapeVariety {
        if (variety == null || variety.isBlank()) {
            throw new IllegalArgumentException("Grape variety cannot be null or empty");
        }
    }

    @JsonValue
    public String getGrapeVariety() {
        return variety;
    }
}

