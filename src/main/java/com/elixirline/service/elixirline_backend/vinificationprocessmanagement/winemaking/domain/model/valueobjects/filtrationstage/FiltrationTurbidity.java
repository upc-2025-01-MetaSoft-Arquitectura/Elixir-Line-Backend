package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FiltrationTurbidity(Double filtrationTurbidity) {
    public FiltrationTurbidity {
        if (filtrationTurbidity != null && filtrationTurbidity < 0) {
            throw new IllegalArgumentException("Turbidity cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getTurbidity() {
        return filtrationTurbidity;
    }

    @JsonCreator
    public static FiltrationTurbidity from(Double filtrationTurbidity) {
        return new FiltrationTurbidity(filtrationTurbidity);
    }
}
