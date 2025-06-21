package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ClarificationVolume(Double volume) {
    public ClarificationVolume {
        if (volume != null && volume < 0) {
            throw new IllegalArgumentException("Volume cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getVolume() {
        return volume;
    }
}
