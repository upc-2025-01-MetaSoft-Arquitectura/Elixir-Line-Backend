package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record CorrectionSugarLevel(Double sugarLevel) {
    public CorrectionSugarLevel {
        if (sugarLevel != null && sugarLevel < 0) {
            throw new IllegalArgumentException("Correction Sugar level cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getSugarLevel() {
        return sugarLevel;
    }

    @JsonCreator
    public static CorrectionSugarLevel from(Double sugarLevel)  {
        return new CorrectionSugarLevel(sugarLevel);
    }
}