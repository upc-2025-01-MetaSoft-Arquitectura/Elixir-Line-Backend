package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record BottlingLine(String bottleLine) {
    public BottlingLine {
        if (bottleLine != null && bottleLine.length() > 50) {
            throw new IllegalArgumentException("Bottling line cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getBottleLine() {
        return bottleLine;
    }

    @JsonCreator
    public static BottlingLine from(String bottleLine) {
        return new BottlingLine(bottleLine);
    }
}
