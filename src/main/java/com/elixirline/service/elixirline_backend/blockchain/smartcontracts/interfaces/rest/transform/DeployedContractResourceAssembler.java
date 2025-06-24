package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.DeployedContractResource;

public class DeployedContractResourceAssembler {
    public static DeployedContractResource toResource(DeployedContract contract) {
        return new DeployedContractResource(
                contract.getId(),
                contract.getContractName().getContractName(),
                contract.getContractAddress().getContractAddress(),
                contract.getDeployedAt().getDeployedAt()
        );
    }
}
