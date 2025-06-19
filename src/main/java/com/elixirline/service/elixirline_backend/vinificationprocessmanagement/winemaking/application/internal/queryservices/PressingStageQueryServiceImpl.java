package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetPressingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage.PressingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.PressingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PressingStageQueryServiceImpl implements PressingStageQueryService {
    private final PressingStageRepository pressingStageRepository;

    @Override
    public Optional<PressingStage> getPressingStageByBatchId(GetPressingStageByBatchIdQuery query) {
        return pressingStageRepository.findByBatchId(query.batchId());
    }
}
