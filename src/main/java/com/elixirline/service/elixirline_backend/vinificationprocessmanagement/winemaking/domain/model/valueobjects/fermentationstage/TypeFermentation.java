package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record TypeFermentation(String typeFermentation) {
    public TypeFermentation {
        if (typeFermentation != null && typeFermentation.length() > 80) {
            throw new IllegalArgumentException("Type fermentation cannot be longer than 80 characters.");
        }
    }

    @JsonValue
    public String getTypeFermentation() {
        return typeFermentation;
    }
}
