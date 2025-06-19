package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AddedAcid(Double addedAcid) {
    public AddedAcid {
        if (addedAcid != null && addedAcid < 0) {
            throw new IllegalArgumentException("Added acid cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getAddedAcid() {
        return addedAcid;
    }

}
