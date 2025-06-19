package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.*;

public record CreateBatchCommand(
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
