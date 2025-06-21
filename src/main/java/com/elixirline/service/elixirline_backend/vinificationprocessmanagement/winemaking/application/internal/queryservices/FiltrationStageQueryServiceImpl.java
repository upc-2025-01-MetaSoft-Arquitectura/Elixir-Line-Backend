package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFiltrationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage.FiltrationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FiltrationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltrationStageQueryServiceImpl implements FiltrationStageQueryService {
    private final FiltrationStageRepository filtrationStageRepository;

    @Override
    public Optional<FiltrationStage> getFiltrationStageByBatchId(GetFiltrationStageByBatchIdQuery query) {
        return filtrationStageRepository.findByBatchId(query.batchId());
    }
}
