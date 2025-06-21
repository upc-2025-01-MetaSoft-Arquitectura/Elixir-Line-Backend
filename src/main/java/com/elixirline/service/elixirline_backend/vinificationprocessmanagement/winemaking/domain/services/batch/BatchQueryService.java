package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetBatchesByVineyardCodeQuery;
import java.util.List;
import java.util.Optional;

public interface BatchQueryService {
    Optional<Batch> handle(GetBatchByIdQuery query);
    List<Batch> handle(GetBatchesByVineyardCodeQuery query);
    List<Batch> handle(GetAllBatchesQuery query);
    List<Batch> getAllByCampaignIdByWinegrowerId(Long winegrowerId, Long campaignId); /*TODO: Obtener todos los lotes de una campaña para un vinicultor especifico*/
    List<Batch> getAllByWinegrowerId(Long winegrowerId);

    List<ProcessStage> getStagesByBatchId(Long batchId);
}
