package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record TotalVolume(Double totalVolume) {
    public TotalVolume {
        if (totalVolume != null && totalVolume < 0) {
            throw new IllegalArgumentException("Total volume cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getTotalVolume() {
        return totalVolume;
    }

    @JsonCreator
    public static TotalVolume from(Double totalVolume) {
        return new TotalVolume(totalVolume);
    }
}
