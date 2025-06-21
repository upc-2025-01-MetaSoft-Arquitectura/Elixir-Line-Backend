package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateEmptyReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.DeleteReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.UpdateReceptionStageCommand;
import java.util.Optional;

public interface ReceptionStageCommandService {
    Optional<ReceptionStage> handle(CreateReceptionStageCommand command);
    Optional<ReceptionStage> handle(CreateEmptyReceptionStageCommand command);
    Optional<ReceptionStage> update(UpdateReceptionStageCommand command);
    void delete(DeleteReceptionStageCommand command);
}
