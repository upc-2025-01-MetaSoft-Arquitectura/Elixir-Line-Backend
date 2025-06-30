package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public interface VinificationProcessCommandService {
    DeployedContract deployContract(DeployContractCommand command) throws Exception;
    TransactionReceipt signStage(SignStageCommand command) throws Exception;
}
