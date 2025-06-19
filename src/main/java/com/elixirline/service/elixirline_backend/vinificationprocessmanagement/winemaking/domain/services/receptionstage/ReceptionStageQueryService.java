package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetReceptionStageByBatchIdQuery;
import java.util.Optional;

public interface ReceptionStageQueryService {
    Optional<ReceptionStage> getReceptionStageByBatchId(GetReceptionStageByBatchIdQuery query);
}
