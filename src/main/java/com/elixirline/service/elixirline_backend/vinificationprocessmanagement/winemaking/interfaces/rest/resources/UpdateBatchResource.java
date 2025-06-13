package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateBatchResource(
        String vineyardCode,
        String vineyardOrigin,
        String grapeVariety,
        Integer harvestCampaign,
        LocalDate receptionDate,
        Double initialGrapeQuantityKg
) { }

