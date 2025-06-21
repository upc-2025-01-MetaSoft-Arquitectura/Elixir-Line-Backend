package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.UpdateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.UpdateBatchResource;

public class UpdateBatchCommandFromResourceAssembler {
    public static UpdateBatchCommand toCommandFromResource(Long batchId, UpdateBatchResource resource) {
        return new UpdateBatchCommand(
                batchId,
                resource.campaignId(),
                resource.vineyardCode(),
                new VineyardOrigin(resource.vineyardOrigin()),
                new GrapeVariety(resource.grapeVariety()),
                new HarvestCampaign(resource.harvestCampaign()),
                new ReceptionDate(resource.receptionDate()),
                new InitialGrapeQuantityKg(resource.initialGrapeQuantityKg())
        );
    }
}

