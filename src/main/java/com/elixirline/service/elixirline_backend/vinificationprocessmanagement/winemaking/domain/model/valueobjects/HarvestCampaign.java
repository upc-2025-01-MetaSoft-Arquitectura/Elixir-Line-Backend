package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record HarvestCampaign(String harvestCampaign) {
    public HarvestCampaign {
        if (harvestCampaign == null || harvestCampaign.isBlank()) {
            throw new IllegalArgumentException("HarvestCampaign cannot be null or empty");
        }
        if (!harvestCampaign.matches("^\\d{4}$")) {
            throw new IllegalArgumentException("HarvestCampaign must be a 4-digit year");
        }
    }

    @JsonValue
    public String getHarvestCampaign() {
        return harvestCampaign;
    }
}
