package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateEmptyBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.DeleteBottlingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.UpdateBottlingStageCommand;

import java.util.Optional;

public interface BottlingStageCommandService {
    Optional<BottlingStage> handle(CreateBottlingStageCommand command);
    Optional<BottlingStage> handle(CreateEmptyBottlingStageCommand command);
    Optional<BottlingStage> update(UpdateBottlingStageCommand command);
    void delete(DeleteBottlingStageByBatchCommand command);
}
