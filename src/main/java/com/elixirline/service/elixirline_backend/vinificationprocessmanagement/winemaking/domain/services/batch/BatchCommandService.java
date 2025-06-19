package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.DeleteBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.PatchBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.UpdateBatchCommand;
import java.util.Optional;

public interface BatchCommandService {
    Optional<Batch> handle(CreateBatchCommand command);
    Optional<Batch> update(UpdateBatchCommand command);
    void patch(PatchBatchCommand command);
    void delete(DeleteBatchCommand command);
}
