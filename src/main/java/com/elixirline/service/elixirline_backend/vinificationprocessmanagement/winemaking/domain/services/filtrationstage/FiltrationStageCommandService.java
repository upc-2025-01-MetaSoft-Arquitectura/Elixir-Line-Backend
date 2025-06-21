package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateEmptyFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.DeleteFiltrationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.UpdateFiltrationStageCommand;

import java.util.Optional;

public interface FiltrationStageCommandService {
    Optional<FiltrationStage> handle(CreateFiltrationStageCommand command);
    Optional<FiltrationStage> handle(CreateEmptyFiltrationStageCommand command);
    Optional<FiltrationStage> update(UpdateFiltrationStageCommand command);
    void delete(DeleteFiltrationStageByBatchCommand command);
}
