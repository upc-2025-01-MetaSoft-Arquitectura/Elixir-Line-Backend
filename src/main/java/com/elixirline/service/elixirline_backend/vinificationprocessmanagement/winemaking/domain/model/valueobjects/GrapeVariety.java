package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record GrapeVariety(String grapeVariety) {
    public GrapeVariety {
        if (grapeVariety == null || grapeVariety.isBlank()) {
            throw new IllegalArgumentException("GrapeVariety cannot be null or empty");
        }
    }

    @JsonValue
    public String getGrapeVariety() {
        return grapeVariety;
    }
}
