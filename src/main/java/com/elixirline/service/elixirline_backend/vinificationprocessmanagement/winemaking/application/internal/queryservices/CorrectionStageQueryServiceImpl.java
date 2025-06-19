package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetCorrectionStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage.CorrectionStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.CorrectionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrectionStageQueryServiceImpl implements CorrectionStageQueryService {
    private final CorrectionStageRepository correctionStageRepository;

    @Override
    public Optional<CorrectionStage> getCorrectionStageByBatchId(GetCorrectionStageByBatchIdQuery query) {
        return correctionStageRepository.findById(query.batchId());
    }
}
