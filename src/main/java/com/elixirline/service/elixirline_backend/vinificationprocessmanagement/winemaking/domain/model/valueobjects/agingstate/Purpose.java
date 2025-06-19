package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Purpose(String purpose) {
    public Purpose {
        if (purpose != null && purpose.length() > 255) {
            throw new IllegalArgumentException("Purpose cannot be longer than 255 characters.");
        }
    }

    @JsonValue
    public String getPurpose() {
        return purpose;
    }
}
