package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchesByVineyardCodeQuery;
import java.util.List;
import java.util.Optional;

public interface BatchQueryService {
    Optional<BatchVineyard> handle(GetBatchByIdQuery query);
    List<BatchVineyard> handle(GetBatchesByVineyardCodeQuery query);
    List<BatchVineyard> handle(GetAllBatchesQuery query);
}
