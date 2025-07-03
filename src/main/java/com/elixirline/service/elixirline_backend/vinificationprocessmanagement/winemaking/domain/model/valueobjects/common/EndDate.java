package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record EndDate(LocalDate endDate) {
    @JsonValue
    public LocalDate getEndDate() {
        return endDate;
    }
}
