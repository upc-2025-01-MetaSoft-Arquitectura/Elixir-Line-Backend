package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Yeast(Double yeast) { //Levedura
    public Yeast {
        if (yeast != null && yeast < 0) {
            throw new IllegalArgumentException("Yeast cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getYeast() {
        return yeast;
    }

    @JsonCreator
    public static Yeast from(Double yeast) {
        return new Yeast(yeast);
    }
}
