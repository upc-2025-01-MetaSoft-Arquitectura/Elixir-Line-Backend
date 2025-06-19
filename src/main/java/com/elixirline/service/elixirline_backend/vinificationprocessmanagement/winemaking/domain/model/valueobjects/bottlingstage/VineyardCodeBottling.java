package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record VineyardCodeBottling(String vineyardCodeBottling) {
    public VineyardCodeBottling {
        if (vineyardCodeBottling != null && vineyardCodeBottling.length() > 30) {
            throw new IllegalArgumentException("Vineyard code cannot be longer than 30 characters.");
        }
    }

    @JsonValue
    public String getVineyardCodeBottling() {
        return vineyardCodeBottling;
    }
}