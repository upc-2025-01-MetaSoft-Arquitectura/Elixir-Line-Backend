package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetReceptionStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage.ReceptionStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ReceptionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionStageQueryServiceImpl  implements ReceptionStageQueryService {
    private final ReceptionStageRepository receptionStageRepository;

    @Override
    public Optional<ReceptionStage> getReceptionStageByBatchId(GetReceptionStageByBatchIdQuery query) {
        return receptionStageRepository.findByBatchId(query.batchId());
    }
}
