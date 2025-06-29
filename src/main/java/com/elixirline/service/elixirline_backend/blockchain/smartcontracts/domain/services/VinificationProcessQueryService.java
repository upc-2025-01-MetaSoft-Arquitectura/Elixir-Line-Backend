package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetLastDeployedContractQuery;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageDetailsResource;

import java.util.Optional;

public interface VinificationProcessQueryService {
    Optional<DeployedContract> getLastDeployedContract(GetLastDeployedContractQuery query);
    boolean isStageSigned(GetStageIsSigned query) throws Exception;
    StageDetailsResource getStageDetails(GetStageDetails query) throws Exception;
}
