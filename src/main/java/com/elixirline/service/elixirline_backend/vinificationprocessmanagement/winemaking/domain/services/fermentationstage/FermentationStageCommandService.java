package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateEmptyFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.DeleteFermentationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.UpdateFermentationStageCommand;
import java.util.Optional;

public interface FermentationStageCommandService {
    Optional<FermentationStage> handle(CreateFermentationStageCommand command);
    Optional<FermentationStage> handle(CreateEmptyFermentationStageCommand command);
    Optional<FermentationStage> update(UpdateFermentationStageCommand command);
    void delete(DeleteFermentationStageByBatchCommand command);
}
