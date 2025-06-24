package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageResource;

public class StageCommandFromResourceAssembler {
    public static SignStageCommand toCommandFromResource(StageResource resource) {
        return new SignStageCommand(
                resource.batchId(),
                resource.stageId(),
                resource.stageName(),
                resource.startDate(),
                resource.endDate(),
                resource.dataHash()
        );
    }
}
