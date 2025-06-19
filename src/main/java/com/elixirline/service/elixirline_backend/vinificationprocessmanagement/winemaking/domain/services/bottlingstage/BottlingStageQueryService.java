package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetBottlingStageByBatchIdQuery;

import java.util.Optional;

public interface BottlingStageQueryService {
    Optional<BottlingStage> getBottlingStageByBatchId(GetBottlingStageByBatchIdQuery query);
}
