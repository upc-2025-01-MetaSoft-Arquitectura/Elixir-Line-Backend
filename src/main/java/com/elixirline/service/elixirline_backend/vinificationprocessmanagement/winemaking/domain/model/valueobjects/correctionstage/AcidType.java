package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AcidType(String acidType) {
    public AcidType {
        if (acidType != null && acidType.length() > 50) {
            throw new IllegalArgumentException("Comment cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getAcidType() {
        return acidType;
    }

    @JsonCreator
    public static AcidType from(String acidType) {
        return new AcidType(acidType);
    }
}
