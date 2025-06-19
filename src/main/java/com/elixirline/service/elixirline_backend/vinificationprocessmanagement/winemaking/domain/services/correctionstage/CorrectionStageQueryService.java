package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetCorrectionStageByBatchIdQuery;
import java.util.Optional;

public interface CorrectionStageQueryService {
    Optional<CorrectionStage> getCorrectionStageByBatchId(GetCorrectionStageByBatchIdQuery query);
}
