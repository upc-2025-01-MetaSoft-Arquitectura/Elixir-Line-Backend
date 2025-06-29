package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.SignStageResponseResource;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.time.Instant;

public class SignStageResponseAssembler {
    public static SignStageResponseResource toResource(SignStageCommand command, TransactionReceipt receipt) {
        return new SignStageResponseResource(
                command.batchId(),
                command.stageId(),
                command.stageName(),
                receipt.getTransactionHash(),
                "SUCCESS",
                "Stage signed successfully",
                Instant.now().toString()
        );
    }
}