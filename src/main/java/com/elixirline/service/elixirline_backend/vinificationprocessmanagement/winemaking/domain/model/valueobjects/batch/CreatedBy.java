package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record CreatedBy(String createdBy) {
    public CreatedBy {
        if (createdBy == null || createdBy.isBlank()) {
            throw new IllegalArgumentException("Created by cannot be null or empty");
        }
    }

    @JsonValue
    public String getCreatedBy() {
        return createdBy;
    }
}
