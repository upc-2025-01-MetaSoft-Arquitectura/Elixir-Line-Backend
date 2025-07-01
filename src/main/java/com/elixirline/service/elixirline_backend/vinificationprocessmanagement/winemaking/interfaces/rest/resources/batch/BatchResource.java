package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.*;

public record BatchResource(
        Long id,
        Long winegrowerId,
        Long campaignId,
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
