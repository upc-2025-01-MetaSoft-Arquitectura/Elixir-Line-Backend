package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.BatchStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.Progress;

public record PatchBatchCommand(
        Long batchId,
        Progress progress,
        BatchStatus status,
        CurrentStage currentStage
) { }
