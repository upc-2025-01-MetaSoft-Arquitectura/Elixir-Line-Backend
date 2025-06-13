package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;

public record UpdateBatchCommand(
        Long batchId,
        Long campaignId,
        String vineyardCode,
        VineyardOrigin vineyardOrigin,
        GrapeVariety grapeVariety,
        HarvestCampaign harvestCampaign,
        ReceptionDate receptionDate,
        InitialGrapeQuantityKg initialGrapeQuantityKg
) { }
