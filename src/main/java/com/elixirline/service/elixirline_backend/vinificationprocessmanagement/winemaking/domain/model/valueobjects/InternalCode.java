package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record InternalCode(String internalCode) {
    public InternalCode {
        if (internalCode == null || internalCode.isBlank()) {
            throw new IllegalArgumentException("InternalCode cannot be null or empty");
        }
        /*
        if (!internalCode.matches("^[A-Z]\\d{4}-[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Invalid InternalCode format. Example: B2024-VINEYARD01");
        }
        */
    }

    @JsonValue
    public String getInternalCode() {
        return internalCode;
    }
}
