package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ContainerType(String containerType) {
    public ContainerType {
        if (containerType != null && containerType.length() > 50) {
            throw new IllegalArgumentException("Container type cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getContainerType() {
        return containerType;
    }
}
