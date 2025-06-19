package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetBottlingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage.BottlingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BottlingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BottlingStageQueryServiceImpl implements BottlingStageQueryService {
    private final BottlingStageRepository bottlingStageRepository;

    @Override
    public Optional<BottlingStage> getBottlingStageByBatchId(GetBottlingStageByBatchIdQuery query) {
        return bottlingStageRepository.findByBatchId(query.batchId());
    }
}
