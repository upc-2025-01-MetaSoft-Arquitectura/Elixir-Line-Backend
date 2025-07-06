package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AddedSulphites(Double addedSulphites) {
    public AddedSulphites {
        if (addedSulphites != null && addedSulphites < 0) {
            throw new IllegalArgumentException("Added sulphites cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getAddedSulphites() {
        return addedSulphites;
    }

    @JsonCreator
    public static AddedSulphites from(Double addedSulphites) {
        return new AddedSulphites(addedSulphites);
    }
}
