package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated.SmartContractVinificationProcess;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageDetailsResource;

public class StageDetailsResourceAssembler {
    public static StageDetailsResource toResource(SmartContractVinificationProcess.StageSignedEventResponse stage) {
        return new StageDetailsResource(
                stage.stageName,
                stage.startDate.longValue(),
                stage.endDate.longValue(),
                stage.dataHash
        );
    }
}
