package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetTransactionInfo;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.BlockchainTransactionQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockchainTransactionQueryServiceImpl implements BlockchainTransactionQueryService {
    private static final String CONTRACT_NAME = "SmartContractVinificationProcess";
    private static final String STAGE_SIGNED_EVENT = "StageSigned";

    private final Web3j web3j;
    private final DeployedContractRepository contractRepository;

    @Override
    public Optional<GetTransactionInfo> getStageSignedTransaction(GetStageDetails query) throws Exception {
        String contractAddress = contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc(CONTRACT_NAME)
                .map(dc -> dc.getContractAddress().getContractAddress())
                .orElseThrow(() -> new RuntimeException("No deployed contract found"));

        Event event = new Event(
                STAGE_SIGNED_EVENT,
                Arrays.asList(
                        TypeReference.create(Uint256.class, true),// batchId
                        TypeReference.create(Uint256.class),// stageId
                        TypeReference.create(Utf8String.class, true),// stageName
                        TypeReference.create(Uint256.class),// startDate
                        TypeReference.create(Uint256.class),// endDate
                        TypeReference.create(Utf8String.class)// dataHash
                )
        );

        String eventSignature = EventEncoder.encode(event);
        String batchIdHex = Numeric.toHexStringWithPrefixZeroPadded(BigInteger.valueOf(query.batchId()), 64);
        String stageNameHash = Hash.sha3String(query.stageName());

        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        )
                .addSingleTopic(eventSignature)// topic[0]
                .addOptionalTopics(batchIdHex)// topic[1]
                .addOptionalTopics(stageNameHash);// topic[3]

        System.out.println("Event Signature: " + eventSignature);
        System.out.println("Batch ID Hex: " + batchIdHex);
        System.out.println("Stage Name Hash: " + stageNameHash);

        List<EthLog.LogResult> logs = web3j.ethGetLogs(filter).send().getLogs();

        System.out.println("Logs found: " + logs.size());
        for (EthLog.LogResult<?> logResult : logs) {
            Log log = (Log) logResult.get();
            System.out.println("Log: " + log);
        }

        if (logs == null || logs.isEmpty()) {
            System.out.println("No logs found for the given filter.");
            return Optional.empty();
        }

        for (EthLog.LogResult<?> logResult : logs) {
            Log log = (Log) logResult.get();
            EthBlock.Block block = web3j.ethGetBlockByHash(log.getBlockHash(), false)
                    .send()
                    .getBlock();

            LocalDateTime blockTime = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(block.getTimestamp().longValue()),
                    ZoneId.systemDefault()
            );

            return Optional.of(new GetTransactionInfo(
                    log.getTransactionHash(),
                    blockTime.toString()
            ));
        }
        return Optional.empty();
    }
}
