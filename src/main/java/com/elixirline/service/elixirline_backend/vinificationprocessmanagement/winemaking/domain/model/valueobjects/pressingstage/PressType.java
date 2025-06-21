package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PressType(String pressType) {
    public PressType {
        if (pressType != null && pressType.length() > 50) {
            throw new IllegalArgumentException("Press type cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getPressType() {
        return pressType;
    }
}
