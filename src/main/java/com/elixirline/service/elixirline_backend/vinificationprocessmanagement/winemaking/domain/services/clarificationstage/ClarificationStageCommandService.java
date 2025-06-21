package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateEmptyClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.DeleteClarificationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.UpdateClarificationStageCommand;
import java.util.Optional;

public interface ClarificationStageCommandService {
    Optional<ClarificationStage> handle(CreateClarificationStageCommand command);
    Optional<ClarificationStage> handle(CreateEmptyClarificationStageCommand command);
    Optional<ClarificationStage> update(UpdateClarificationStageCommand command);
    void delete(DeleteClarificationStageByBatchCommand command);
}
