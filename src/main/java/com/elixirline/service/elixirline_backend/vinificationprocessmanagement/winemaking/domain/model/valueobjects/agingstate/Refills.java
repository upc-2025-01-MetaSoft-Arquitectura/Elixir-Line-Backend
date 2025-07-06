package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Refills(Integer refills) {
    public Refills {
        if (refills != null && refills < 0) {
            throw new IllegalArgumentException("Refills cannot be less than zero.");
        }
    }

    @JsonValue
    public Integer getRefills() {
        return refills;
    }

    @JsonCreator
    public static Refills from(Integer refills) {
        return new Refills(refills);
    }
}
