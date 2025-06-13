package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.CreateBatchResource;

public class CreateBatchCommandFromResourceAssembler {
    public static CreateBatchCommand toCommandFromResource(CreateBatchResource resource) {
        return new CreateBatchCommand(
                resource.vineyardCode(),
                resource.vineyardOrigin(),
                resource.grapeVariety(),
                resource.harvestCampaign(),
                resource.receptionDate(),
                resource.initialGrapeQuantityKg(),
                resource.createdBy()
        );
    }
}

