package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record VineyardOrigin(String name) {
    public VineyardOrigin {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Vineyard origin cannot be null or empty");
        }
    }

    @JsonValue
    public String getVineyardOrigin() {
        return name;
    }
}

