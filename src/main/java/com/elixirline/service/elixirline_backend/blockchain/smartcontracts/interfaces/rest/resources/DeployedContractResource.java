package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources;

public record DeployedContractResource(
        Long id,
        String contractName,
        String contractAddress,
        String deployedAt
) { }
