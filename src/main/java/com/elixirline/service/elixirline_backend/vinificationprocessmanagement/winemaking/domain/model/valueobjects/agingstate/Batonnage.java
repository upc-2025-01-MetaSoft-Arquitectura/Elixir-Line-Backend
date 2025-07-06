package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Batonnage(Double batonnage) {
    public Batonnage {
        if (batonnage != null && batonnage < 0) {
            throw new IllegalArgumentException("Batonnage cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getBatonnage() {
        return batonnage;
    }

    @JsonCreator
    public static Batonnage from(Double batonnage) {
        return new Batonnage(batonnage);
    }
}