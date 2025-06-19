package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchesByVineyardCodeQuery;
import java.util.List;
import java.util.Optional;

public interface BatchQueryService {
    Optional<Batch> handle(GetBatchByIdQuery query);
    List<Batch> handle(GetBatchesByVineyardCodeQuery query);
    List<Batch> handle(GetAllBatchesQuery query);
    List<Batch> getAllByCampaignId(Long campaignId);
    List<Batch> getAllByWinegrowerId(Long winegrowerId);
}
