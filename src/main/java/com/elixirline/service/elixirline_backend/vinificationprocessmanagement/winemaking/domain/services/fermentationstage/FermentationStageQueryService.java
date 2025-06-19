package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFermentationStageByBatchIdQuery;
import java.util.Optional;

public interface FermentationStageQueryService {
    Optional<FermentationStage> getFermentationStageByBatchId(GetFermentationStageByBatchIdQuery query);
}
