package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.BatchResource;

public class BatchResourceAssembler {
    public static BatchResource toResource(BatchVineyard batch) {
        return new BatchResource(
                batch.getBatchId(),
                batch.getVineyardCode(),
                batch.getReceptionDate(),
                batch.getHarvestCampaign(),
                batch.getVineyardOrigin(),
                batch.getGrapeVariety(),
                batch.getInitialGrapeQuantityKg(),
                batch.getCreatedBy(),
                batch.getProgress(),
                batch.getStatus(),
                batch.getCurrentStage()
        );
    }
}

