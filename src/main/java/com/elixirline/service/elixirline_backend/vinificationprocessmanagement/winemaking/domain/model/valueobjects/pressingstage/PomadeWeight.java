package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PomadeWeight(Double pomadeWeight) {
    public PomadeWeight {
        if (pomadeWeight != null && pomadeWeight < 0) {
            throw new IllegalArgumentException("Pomace weight cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getPomadeWeight() {
        return pomadeWeight;
    }

    @JsonCreator
    public static PomadeWeight from(Double pomadeWeight) {
        return new PomadeWeight(pomadeWeight);
    }
}