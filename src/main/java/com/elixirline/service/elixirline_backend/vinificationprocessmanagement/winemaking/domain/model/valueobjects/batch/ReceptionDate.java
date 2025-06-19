package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
public record ReceptionDate(LocalDate date) {
    public ReceptionDate {
        if (date == null) {
            throw new IllegalArgumentException("Reception date cannot be null");
        }
    }

    @JsonValue
    public LocalDate getReceptionDate() {
        return date;
    }
}

