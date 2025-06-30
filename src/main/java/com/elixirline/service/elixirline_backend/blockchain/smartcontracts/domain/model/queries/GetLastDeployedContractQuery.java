package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractName;

public record GetLastDeployedContractQuery(ContractName contractName) { }
