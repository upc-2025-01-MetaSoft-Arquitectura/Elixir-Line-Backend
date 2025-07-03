package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record StartDate(LocalDate startDate) {
    @JsonValue
    public LocalDate getStartDate() {
        return startDate;
    }
}
