package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ReceptionPHLevel(Double receptionPHLevel) {
    public ReceptionPHLevel {
        if (receptionPHLevel != null && receptionPHLevel < 0) {
            throw new IllegalArgumentException("Reception pH cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getPHLevel() {
        return receptionPHLevel;
    }
}
