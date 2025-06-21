package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateEmptyCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.DeleteCorrectionStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.UpdateCorrectionStageCommand;
import java.util.Optional;

public interface CorrectionStageCommandService {
    Optional<CorrectionStage> handle(CreateCorrectionStageCommand command);
    Optional<CorrectionStage> handle(CreateEmptyCorrectionStageCommand command);
    Optional<CorrectionStage> update(UpdateCorrectionStageCommand command);
    void delete(DeleteCorrectionStageByBatchCommand command);
}
