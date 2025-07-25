package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record EndDate(LocalDate endDate) {
    public EndDate {

    }

    @JsonValue
    public LocalDate getEndDate() {
        return endDate;
    }

    @JsonCreator
    public static EndDate from(LocalDate endDate) {
        return new EndDate(endDate);
    }
}
