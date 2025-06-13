package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;

public record BatchResource(
        Long batchId,
        Long campaignId,
        Long winegrowerId,
        String vineyardCode,
        ReceptionDate receptionDate,
        HarvestCampaign harvestCampaign,
        VineyardOrigin vineyardOrigin,
        GrapeVariety grapeVariety,
        InitialGrapeQuantityKg initialGrapeQuantityKg,
        CreatedBy createdBy,
        Progress progress,
        BatchStatus status,
        CurrentStage currentStage
) { }
