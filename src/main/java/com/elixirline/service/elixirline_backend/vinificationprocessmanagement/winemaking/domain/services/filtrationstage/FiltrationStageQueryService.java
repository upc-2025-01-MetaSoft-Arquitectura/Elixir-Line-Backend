package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFiltrationStageByBatchIdQuery;
import java.util.Optional;

public interface FiltrationStageQueryService {
    Optional<FiltrationStage> getFiltrationStageByBatchId(GetFiltrationStageByBatchIdQuery query);
}
