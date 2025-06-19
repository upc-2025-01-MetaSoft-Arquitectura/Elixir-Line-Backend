package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetClarificationStageByBatchIdQuery;
import java.util.Optional;

public interface ClarificationStageQueryService {
    Optional<ClarificationStage> getClarificationStageByBatchId(GetClarificationStageByBatchIdQuery query);
}
