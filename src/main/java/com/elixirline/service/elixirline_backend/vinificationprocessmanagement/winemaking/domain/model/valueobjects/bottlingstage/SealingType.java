package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record SealingType(String sealingType) {
    public SealingType {
        if (sealingType != null && sealingType.length() > 50) {
            throw new IllegalArgumentException("Sealing type cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getSealingType() {
        return sealingType;
    }
}
