package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.UpdateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.UpdateBatchResource;

public class UpdateBatchCommandFromResourceAssembler {
    public static UpdateBatchCommand toCommandFromResource(Long batchId, UpdateBatchResource resource) {
        return new UpdateBatchCommand(
                batchId,
                resource.vineyardCode(),
                new VineyardOrigin(resource.vineyardOrigin()),
                new GrapeVariety(resource.grapeVariety()),
                new HarvestCampaign(resource.harvestCampaign()),
                new ReceptionDate(resource.receptionDate()),
                new InitialGrapeQuantityKg(resource.initialGrapeQuantityKg())
        );
    }
}

