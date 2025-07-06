package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Justification(String justification) {
    public Justification {
        if ( justification != null && justification.length() > 255) {
            throw new IllegalArgumentException("Justification cannot be longer than 255 characters.");
        }
    }

    @JsonValue
    public String getJustification() {
        return justification;
    }

    @JsonCreator
    public static Justification from(String justification) {
        return new Justification(justification);
    }
}
