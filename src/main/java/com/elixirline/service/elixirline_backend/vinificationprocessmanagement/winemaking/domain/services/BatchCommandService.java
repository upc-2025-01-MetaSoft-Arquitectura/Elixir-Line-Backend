package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.DeleteBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.PatchBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.UpdateBatchCommand;
import java.util.Optional;

public interface BatchCommandService {
    Optional<BatchVineyard> handle(CreateBatchCommand command);
    Optional<BatchVineyard> update(UpdateBatchCommand command);
    void patch(PatchBatchCommand command);
    void delete(DeleteBatchCommand command);
}
