package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.PatchBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.Progress;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.PatchBatchResource;

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

