package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record CreatedBy(String name) {
    public CreatedBy {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Created by cannot be null or empty");
        }
    }

    @JsonValue
    public String getCreatedBy() {
        return name;
    }
}
