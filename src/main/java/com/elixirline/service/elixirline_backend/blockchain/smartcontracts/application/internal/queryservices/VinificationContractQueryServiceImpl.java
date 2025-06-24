package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated.SmartContractVinificationProcess;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetLastDeployedContractQuery;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinificationContractQueryServiceImpl implements VinificationProcessQueryService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final DeployedContractRepository contractRepository;

    @Value("${web3.gas.limit}")
    private BigInteger gasLimit;

    @Value("${web3.gas.price}")
    private BigInteger gasPrice;

    private SmartContractVinificationProcess loadLatestContract() {
        return contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc("SmartContractVinificationProcess")
                .map(deployed -> SmartContractVinificationProcess.load(
                        deployed.getContractAddress().getContractAddress(),
                        web3j,
                        credentials,
                        new DefaultGasProvider() {
                            @Override
                            public BigInteger getGasLimit() { return gasLimit; }

                            @Override
                            public BigInteger getGasPrice() { return gasPrice; }
                        }
                ))
                .orElseThrow(() -> new RuntimeException("No deployed contract found"));
    }

    public SmartContractVinificationProcess getContract() {
        return loadLatestContract();
    }

    @Override
    public Optional<DeployedContract> getLastDeployedContract(GetLastDeployedContractQuery query) {
        return contractRepository.findTopByContractNameContractNameOrderByDeployedAtDesc(query.contractName().getContractName());
    }

    @Override
    public boolean isStageSigned(GetStageIsSigned query) throws Exception {
        BigInteger batchId = BigInteger.valueOf(query.batchId());
        BigInteger stageId = BigInteger.valueOf(query.stageId());
        return this.getContract().isStageSigned(batchId, stageId).send();
    }

    @Override
    public SmartContractVinificationProcess.StageSignedEventResponse getStageDetails(GetStageDetails query) throws Exception {
        BigInteger batchId = BigInteger.valueOf(query.batchId());
        BigInteger stageId = BigInteger.valueOf(query.stageId());
        RemoteCall<Tuple5<String, BigInteger, BigInteger, String, Boolean>> remoteCall = this.getContract().getStageDetails(batchId, stageId);
        Tuple5<String, BigInteger, BigInteger, String, Boolean> result = remoteCall.send();
        SmartContractVinificationProcess.StageSignedEventResponse response = new SmartContractVinificationProcess.StageSignedEventResponse();

        response.batchId = batchId;
        response.stageId = stageId;
        response.stageName = result.component1();  // stageName
        response.startDate = result.component2();  // startDate
        response.endDate = result.component3();    // endDate
        response.dataHash = result.component4();   // dataHash
        return response;
    }
}


