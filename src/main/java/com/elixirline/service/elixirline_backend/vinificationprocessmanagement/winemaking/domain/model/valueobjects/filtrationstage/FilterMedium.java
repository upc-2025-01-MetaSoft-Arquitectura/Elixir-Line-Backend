package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record FilterMedium(String filterMedium) {
    public FilterMedium {
        if (filterMedium != null && filterMedium.length() > 50) {
            throw new IllegalArgumentException("Filter medium cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getFilterMedium() {
        return filterMedium;
    }
}