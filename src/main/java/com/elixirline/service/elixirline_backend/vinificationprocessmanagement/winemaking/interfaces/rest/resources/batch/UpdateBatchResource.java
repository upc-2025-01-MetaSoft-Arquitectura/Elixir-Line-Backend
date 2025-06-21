package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch;

import java.time.LocalDate;

public record UpdateBatchResource(
        Long campaignId,
        String vineyardCode,
        String vineyardOrigin,
        String grapeVariety,
        Integer harvestCampaign,
        LocalDate receptionDate,
        Double initialGrapeQuantityKg
) { }

