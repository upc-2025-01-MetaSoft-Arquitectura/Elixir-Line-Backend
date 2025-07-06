package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FermentationTemperature(Double fermentationTemperature) {
    public FermentationTemperature {
        if (fermentationTemperature != null && fermentationTemperature < 0) {
            throw new IllegalArgumentException("Fermentation Temperature cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getFermentationTemperature() {
        return fermentationTemperature;
    }

    @JsonCreator
    public static FermentationTemperature from(Double fermentationTemperature) {
        return new FermentationTemperature(fermentationTemperature);
    }
}
