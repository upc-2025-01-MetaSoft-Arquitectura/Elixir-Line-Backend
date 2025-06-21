package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.DeletePressingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.UpdatePressingStageCommand;
import java.util.Optional;

public interface PressingStageCommandService {
    Optional<PressingStage> handle(CreatePressingStageCommand command);
    Optional<PressingStage> update(UpdatePressingStageCommand command);
    void delete(DeletePressingStageByBatchCommand command);
}
