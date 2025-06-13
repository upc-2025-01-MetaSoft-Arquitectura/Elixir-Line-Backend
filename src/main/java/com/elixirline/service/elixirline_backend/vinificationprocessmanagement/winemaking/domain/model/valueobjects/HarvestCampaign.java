package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record HarvestCampaign(int year) {
    public HarvestCampaign {
        if (year < 0) {
            throw new IllegalArgumentException("Harvest campaign year cannot be negative");
        }
    }

    @JsonValue
    public int getHarvestCampaign() {
        return year;
    }
}

