package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record VineyardOrigin(String vineyardOrigin) {
    public VineyardOrigin {
        if (vineyardOrigin == null || vineyardOrigin.isBlank()) {
            throw new IllegalArgumentException("VineyardOrigin cannot be null or empty");
        }
    }

    @JsonValue
    public String getVineyardOrigin() {
        return vineyardOrigin;
    }
}
