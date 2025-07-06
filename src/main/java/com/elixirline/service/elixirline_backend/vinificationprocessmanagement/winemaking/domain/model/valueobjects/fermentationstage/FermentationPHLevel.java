package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FermentationPHLevel(Double fermentationPHLevel) {
    public FermentationPHLevel {
        if (fermentationPHLevel != null && fermentationPHLevel < 0) {
            throw new IllegalArgumentException("Fermentation pH cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getFermentationPHLevel() {
        return fermentationPHLevel;
    }

    @JsonCreator
    public static FermentationPHLevel from(Double fermentationPHLevel) {
        return new FermentationPHLevel(fermentationPHLevel);
    }
}
