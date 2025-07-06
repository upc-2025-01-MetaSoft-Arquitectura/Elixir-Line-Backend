package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record AgingDuration(Integer agingDuration) {
    public AgingDuration {
        if (agingDuration != null && agingDuration < 0) {
            throw new IllegalArgumentException("Duration cannot be less than zero.");
        }
    }

    @JsonValue
    public Integer getAgingDuration() {
        return agingDuration;
    }

    @JsonCreator
    public static AgingDuration from(Integer agingDuration) {
        return new AgingDuration(agingDuration);
    }
}