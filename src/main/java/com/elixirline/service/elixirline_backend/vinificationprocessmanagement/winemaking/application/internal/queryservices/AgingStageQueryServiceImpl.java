package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.AgingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetAgingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage.AgingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.AgingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgingStageQueryServiceImpl implements AgingStageQueryService {
    private final AgingStageRepository agingStageRepository;

    @Override
    public Optional<AgingStage> getAgingStageByBatchId(GetAgingStageByBatchIdQuery query) {
        return agingStageRepository.findByBatchId(query.batchId());
    }
}
