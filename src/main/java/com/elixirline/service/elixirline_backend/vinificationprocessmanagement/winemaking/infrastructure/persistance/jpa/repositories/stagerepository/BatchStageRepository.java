package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.stagerepository;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import java.util.List;

public interface BatchStageRepository {
    List<ProcessStage> findStagesByBatchId(Long batchId);
}
