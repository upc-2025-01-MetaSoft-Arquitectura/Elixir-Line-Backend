package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.*;

public record CreateBatchResource(
        Long winegrowerId,
        Long campaignId,
        String vineyardCode,
        VineyardOrigin vineyardOrigin,
        GrapeVariety grapeVariety,
        HarvestCampaign harvestCampaign,
        ReceptionDate receptionDate,
        InitialGrapeQuantityKg initialGrapeQuantityKg,
        CreatedBy createdBy
) { }
