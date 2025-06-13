package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
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
    public Optional<BatchVineyard> handle(GetBatchByIdQuery query) {
        return Optional.ofNullable(batchRepository.findById(query.batchId()).orElseThrow(() -> new BatchNotFoundException(query.batchId())));
    }

    @Override
    public List<BatchVineyard> handle(GetBatchesByVineyardCodeQuery query) {
        return batchRepository.findByVineyardCode(query.vineyardCode());
    }

    @Override
    public List<BatchVineyard> handle(GetAllBatchesQuery query) {
        return batchRepository.findAll();
    }
}
