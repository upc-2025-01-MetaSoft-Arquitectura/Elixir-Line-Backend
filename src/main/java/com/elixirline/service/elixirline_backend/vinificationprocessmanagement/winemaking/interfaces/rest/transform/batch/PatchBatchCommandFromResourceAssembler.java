package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.PatchBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.Progress;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.PatchBatchResource;

public class PatchBatchCommandFromResourceAssembler {
    public static PatchBatchCommand toCommandFromResource(Long batchId, PatchBatchResource resource) {
        Progress progress = resource.progress() != null ? new Progress(resource.progress()) : null;
        return new PatchBatchCommand(
                batchId,
                progress,
                resource.status(),
                resource.currentStage()
        );
    }

}

