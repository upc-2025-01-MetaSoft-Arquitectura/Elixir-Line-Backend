package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.TransactionDetailsNotBeRetrieved;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated.SmartContractVinificationProcess;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetLastDeployedContractQuery;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetTransactionInfo;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.BlockchainTransactionQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageDetailsResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.gas.DefaultGasProvider;
import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinificationContractQueryServiceImpl implements VinificationProcessQueryService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final DeployedContractRepository contractRepository;
    private final BlockchainTransactionQueryService blockchainTransactionQueryService;


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
        return getContract()
                .isStageSigned(BigInteger.valueOf(
                        query.batchId()),
                        query.stageName()
                ).send();
    }

    @Override
    public StageDetailsResource getStageDetails(GetStageDetails query) throws Exception {
        SmartContractVinificationProcess contract = getContract();
        BigInteger batchId = BigInteger.valueOf(query.batchId());

        // 1. Obtener detalles de la etapa desde el contrato
        Tuple6<BigInteger, String, BigInteger, BigInteger, String, Boolean> stageDetails =
                contract.getStageDetails(batchId, query.stageName()).send();

        // 2. Obtener metadatos de la transacción
        GetTransactionInfo transactionInfo = blockchainTransactionQueryService
                .getStageSignedTransaction(query)
                .orElseThrow(() ->
                        new TransactionDetailsNotBeRetrieved(
                                "UNKNOWN",
                                "StageDetails for batch: " + query.batchId() +
                                        ", stage: " + query.stageName(),
                                null
                        )
                );

        // 3. Mapear y convertir los datos
        return StageDetailsResource.fromBlockchain(
                query.batchId(),
                stageDetails.component1().longValue(),
                stageDetails.component2(),
                stageDetails.component3().longValue(),
                stageDetails.component4().longValue(),
                stageDetails.component5(),
                transactionInfo.transactionHash(),
                transactionInfo.signatureDate(),
                Boolean.TRUE.equals(stageDetails.component6())
        );
    }
}


