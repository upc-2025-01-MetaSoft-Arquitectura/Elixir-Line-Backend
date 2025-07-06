package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FermentationSugarLevel(Double fermentationSugarLevel) {
    public FermentationSugarLevel {
        if(fermentationSugarLevel != null && fermentationSugarLevel < 0) {
            throw new IllegalArgumentException("Fermentation sugar level cannot be negative.");
        }
    }

    @JsonValue
    public Double getFermentationSugarLevel() {
        return fermentationSugarLevel;
    }

    @JsonCreator
    public static FermentationSugarLevel from(Double fermentationSugarLevel) {
        return new FermentationSugarLevel(fermentationSugarLevel);
    }
}
