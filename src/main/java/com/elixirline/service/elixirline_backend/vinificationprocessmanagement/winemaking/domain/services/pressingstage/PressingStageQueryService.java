package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetPressingStageByBatchIdQuery;
import java.util.Optional;

public interface PressingStageQueryService {
    Optional<PressingStage> getPressingStageByBatchId(GetPressingStageByBatchIdQuery query);
}
