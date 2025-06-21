package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Porosity(Double porosity) {
    public Porosity {
        if (porosity != null && porosity < 0) {
            throw new IllegalArgumentException("Porosity cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getPorosity() {
        return porosity;
    }
}
