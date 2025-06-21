package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.AgingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetAgingStageByBatchIdQuery;
import java.util.Optional;

public interface AgingStageQueryService {
    Optional<AgingStage> getAgingStageByBatchId(GetAgingStageByBatchIdQuery query);
}
