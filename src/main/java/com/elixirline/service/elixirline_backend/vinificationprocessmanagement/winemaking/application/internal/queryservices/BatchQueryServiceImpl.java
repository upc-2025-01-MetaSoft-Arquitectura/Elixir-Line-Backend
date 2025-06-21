package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetAllBatchesByCapaignIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetBatchesByVineyardCodeQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.batch.BatchQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.stagerepository.BatchStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchQueryServiceImpl implements BatchQueryService {
    private final BatchRepository batchRepository;
    private final BatchStageRepository batchStageRepository;

    @Override
    public Optional<Batch> handle(GetBatchByIdQuery query) {
        return Optional.ofNullable(batchRepository.findById(query.batchId()).orElseThrow(() -> new BatchNotFoundException(query.batchId())));
    }

    @Override
    public List<Batch> handle(GetBatchesByVineyardCodeQuery query) {
        return batchRepository.findByVineyardCode(query.vineyardCode());
    }

    @Override
    public List<Batch> handle(GetAllBatchesQuery query) {
        return batchRepository.findAll();
    }

    @Override
    public List<Batch> getAllBatchesByCampaignId(GetAllBatchesByCapaignIdQuery query) {
        return batchRepository.findByCampaignId(query.campaignId());
    }


    @Override
    public List<Batch> getAllByWinegrowerId(Long winegrowerId) {
        return batchRepository.findByWinegrowerId(winegrowerId);
    }

    @Override
    public List<ProcessStage> getStagesByBatchId(Long batchId) {
        if (!batchRepository.existsById(batchId)) {
            throw new BatchNotFoundException(batchId);
        }

        return batchStageRepository.findStagesByBatchId(batchId);
    }
}
