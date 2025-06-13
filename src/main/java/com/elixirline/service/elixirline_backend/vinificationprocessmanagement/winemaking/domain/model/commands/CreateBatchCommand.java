package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;

public record CreateBatchCommand(
        String vineyardCode,
        VineyardOrigin vineyardOrigin,
        GrapeVariety grapeVariety,
        HarvestCampaign harvestCampaign,
        ReceptionDate receptionDate,
        InitialGrapeQuantityKg initialGrapeQuantityKg,
        CreatedBy createdBy
) { }
