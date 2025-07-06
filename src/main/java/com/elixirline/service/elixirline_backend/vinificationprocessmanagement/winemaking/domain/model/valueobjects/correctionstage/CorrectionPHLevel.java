package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record CorrectionPHLevel(Double correctionPHLevel) {
    public CorrectionPHLevel {
        if (correctionPHLevel != null && correctionPHLevel < 0) {
            throw new IllegalArgumentException("Correction pH cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getCorrectionPHLevel() {
        return correctionPHLevel;
    }

    @JsonCreator
    public static CorrectionPHLevel from(Double correctionPHLevel) {
        return new CorrectionPHLevel(correctionPHLevel);
    }
}
