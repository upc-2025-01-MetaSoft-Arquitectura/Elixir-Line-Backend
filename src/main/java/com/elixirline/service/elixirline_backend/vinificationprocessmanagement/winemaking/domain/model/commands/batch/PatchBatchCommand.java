package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.BatchStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.Progress;

public record PatchBatchCommand(
        Long batchId,
        Progress progress,
        BatchStatus status,
        CurrentStage currentStage
) { }
