package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.CreateBatchResource;

public class CreateBatchCommandFromResourceAssembler {
    public static CreateBatchCommand toCommandFromResource(CreateBatchResource resource) {
        return new CreateBatchCommand(
                resource.winegrowerId(),
                resource.campaignId(),
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

