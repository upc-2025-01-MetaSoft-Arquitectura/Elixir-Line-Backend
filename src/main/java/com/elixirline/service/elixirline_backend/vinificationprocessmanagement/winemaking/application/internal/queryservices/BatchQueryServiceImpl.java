package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchesByVineyardCodeQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.BatchQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchQueryServiceImpl implements BatchQueryService {
    private final BatchRepository batchRepository;

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
    public List<Batch> getAllByCampaignId(Long campaignId) {
        return batchRepository.findByCampaignId(campaignId);
    }

    @Override
    public List<Batch> getAllByWinegrowerId(Long winegrowerId) {
        return batchRepository.findByWinegrowerId(winegrowerId);
    }
}
