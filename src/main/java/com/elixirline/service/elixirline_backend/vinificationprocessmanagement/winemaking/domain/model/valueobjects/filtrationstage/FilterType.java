package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FilterType(String filterType) {
    public FilterType {
        if (filterType != null && filterType.length() > 50) {
            throw new IllegalArgumentException("Filter type cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getFilterType() {
        return filterType;
    }
}
