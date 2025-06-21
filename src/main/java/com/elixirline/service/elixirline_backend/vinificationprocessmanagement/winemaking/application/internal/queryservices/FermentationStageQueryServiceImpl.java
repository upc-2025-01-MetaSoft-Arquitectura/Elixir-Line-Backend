package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFermentationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage.FermentationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FermentationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FermentationStageQueryServiceImpl implements FermentationStageQueryService {
    private final FermentationStageRepository fermentationStageRepository;

    @Override
    public Optional<FermentationStage> getFermentationStageByBatchId(GetFermentationStageByBatchIdQuery query) {
        return fermentationStageRepository.findByBatchId(query.batchId());
    }
}
