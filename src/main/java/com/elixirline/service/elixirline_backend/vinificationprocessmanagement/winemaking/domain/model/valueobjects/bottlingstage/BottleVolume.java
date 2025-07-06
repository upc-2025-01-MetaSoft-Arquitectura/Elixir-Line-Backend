package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record BottleVolume(Double bottleVolume) {
    public BottleVolume {
        if (bottleVolume != null && bottleVolume <= 0) {
            throw new IllegalArgumentException("Bottle volume must be greater than zero.");
        }
    }

    @JsonValue
    public Double getBottleVolume() {
        return bottleVolume;
    }

    @JsonCreator
    public static BottleVolume from(Double bottleVolume) {
        return new BottleVolume(bottleVolume);
    }
}
