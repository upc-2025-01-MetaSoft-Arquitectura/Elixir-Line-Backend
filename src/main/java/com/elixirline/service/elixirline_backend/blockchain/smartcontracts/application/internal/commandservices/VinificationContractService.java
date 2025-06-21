package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.generated.VinificationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class VinificationContractService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final VinificationProcess contract;

    @Autowired
    public VinificationContractService(Web3j web3j) throws Exception {
        this.web3j = web3j;
        this.credentials = Credentials.create("0x0b54a130684478fb47bed73610139280557ab4d61925b09b7c4963bc47bd88eb");
        this.gasProvider = new DefaultGasProvider();
        String contractAddress = "0x1e64a00373B2a973a22d8886a2488acC7DfAf831";
        this.contract = VinificationProcess.load(contractAddress, web3j, credentials, gasProvider);
    }

    public void signStage(String batchId, String stageName, String dataHash) throws Exception {
        contract.signStage(batchId, stageName, dataHash).send();
    }

    public boolean isStageSigned(String batchId, String stageName) throws Exception {
        return contract.isStageSigned(batchId, stageName).send();
    }
}
