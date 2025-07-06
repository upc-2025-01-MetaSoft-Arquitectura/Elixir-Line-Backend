package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AgingVolume(Double agingVolume) {
    public AgingVolume {
        if (agingVolume != null && agingVolume < 0) {
            throw new IllegalArgumentException("Volume cannot be less than zero.");
        }
    }

    @JsonValue
    public Double getAgingVolume() {
        return agingVolume;
    }

    @JsonCreator
    public static AgingVolume from(Double agingVolume) {
        return new AgingVolume(agingVolume);
    }
}