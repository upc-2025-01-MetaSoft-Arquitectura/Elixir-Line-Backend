package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FiltrationVolume(Double filtrationVolume) {
    public FiltrationVolume {
        if (filtrationVolume != null && filtrationVolume < 0) {
            throw new IllegalArgumentException("Filtered volume cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getFiltrationVolume() {
        return filtrationVolume;
    }
}