package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.AgingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateEmptyAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.DeleteAgingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.UpdateAgingStageCommand;
import java.util.Optional;

public interface AgingStageCommandService {
    Optional<AgingStage> handle(CreateAgingStageCommand command);
    Optional<AgingStage> handle(CreateEmptyAgingStageCommand command);
    Optional<AgingStage> update(UpdateAgingStageCommand command);
    void delete(DeleteAgingStageByBatchCommand command);
}
