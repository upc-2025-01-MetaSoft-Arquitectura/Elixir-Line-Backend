package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record VineyardOrigin(String vineyardOrigin) {
    public VineyardOrigin {
        if (vineyardOrigin == null || vineyardOrigin.isBlank()) {
            throw new IllegalArgumentException("Vineyard origin cannot be null or empty");
        }
    }

    @JsonValue
    public String getVineyardOrigin() {
        return vineyardOrigin;
    }

    @JsonCreator
    public static VineyardOrigin from(String vineyardOrigin) {
        return new VineyardOrigin(vineyardOrigin);
    }
}

