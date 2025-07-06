package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ReceptionSugarLevel(Double sugarLevel) {
    public ReceptionSugarLevel {
        if (sugarLevel != null && sugarLevel < 0) {
            throw new IllegalArgumentException("Reception Sugar level cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getReceptionSugarLevel() {
        return sugarLevel;
    }

    @JsonCreator
    public static ReceptionSugarLevel from(Double sugarLevel) {
        return new ReceptionSugarLevel(sugarLevel);
    }
}
