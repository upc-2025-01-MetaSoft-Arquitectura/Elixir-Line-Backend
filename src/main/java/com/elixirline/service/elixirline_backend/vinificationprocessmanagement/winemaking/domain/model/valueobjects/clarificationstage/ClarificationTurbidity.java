package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ClarificationTurbidity(Double turbidity) {
    public ClarificationTurbidity {
        if (turbidity != null && turbidity < 0) {
            throw new IllegalArgumentException("Turbidity cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getTurbidity() {
        return turbidity;
    }
}
