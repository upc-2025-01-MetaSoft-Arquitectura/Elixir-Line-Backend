package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;

public record CreateBatchResource(
        String vineyardCode,
        VineyardOrigin vineyardOrigin,
        GrapeVariety grapeVariety,
        HarvestCampaign harvestCampaign,
        ReceptionDate receptionDate,
        InitialGrapeQuantityKg initialGrapeQuantityKg,
        CreatedBy createdBy
) { }
