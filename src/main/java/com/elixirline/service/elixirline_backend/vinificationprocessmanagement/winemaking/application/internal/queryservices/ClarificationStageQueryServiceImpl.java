package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetClarificationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage.ClarificationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ClarificationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClarificationStageQueryServiceImpl implements ClarificationStageQueryService {
    private final ClarificationStageRepository clarificationStageRepository;

    @Override
    public Optional<ClarificationStage> getClarificationStageByBatchId(GetClarificationStageByBatchIdQuery query) {
        return clarificationStageRepository.findByBatchId(query.batchId());
    }
}
