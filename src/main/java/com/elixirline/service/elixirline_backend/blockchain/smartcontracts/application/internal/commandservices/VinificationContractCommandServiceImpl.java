package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated.SmartContractVinificationProcess;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractAddress;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractName;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.DeployedAt;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessCommandService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinificationContractCommandServiceImpl implements VinificationProcessCommandService {
    private final Web3j web3j;
    private final Credentials credentials;
    private final DeployedContractRepository contractRepository;
    private static Logger log = LoggerFactory.getLogger(VinificationContractCommandServiceImpl.class);

    @Value("${web3.gas.limit}")
    private BigInteger gasLimit;

    @Value("${web3.gas.price}")
    private BigInteger gasPrice;


    @Override
    @Transactional
    public DeployedContract deployContract(DeployContractCommand command) throws Exception {
        final String CONTRACT_NAME = "SmartContractVinificationProcess";
        ContractName contractName = new ContractName(CONTRACT_NAME);

        // 1. Verificar si ya existe un contrato desplegado
        Optional<DeployedContract> existing = contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc(CONTRACT_NAME);

        if (existing.isPresent()) {
            log.info("Usando contrato existente: {}", existing.get().getContractAddress());
            return existing.get();
        }

        // Si no existe, desplegar uno nuevo SmartContract
        return deployNewContract(contractName);
    }


    private DeployedContract deployNewContract(ContractName contractName) throws Exception {
        log.info("Iniciando despliegue de nuevo contrato...");

        try {
            // 2. Desplegar el contrato
            SmartContractVinificationProcess contract = SmartContractVinificationProcess.deploy(
                    web3j,
                    credentials,
                    new DefaultGasProvider() {
                        @Override public BigInteger getGasLimit() { return gasLimit; }
                        @Override public BigInteger getGasPrice() { return gasPrice; }
                    }
            ).send();

            if (contract == null || contract.getContractAddress() == null) {
                throw new RuntimeException("El contrato no generó una dirección válida");
            }

            log.info("Contrato desplegado exitosamente en: {}", contract.getContractAddress());
            return saveDeployedContract(contractName, contract.getContractAddress());

        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("invalid opcode")) {
                throw new RuntimeException("No se pudo verificar el despliegue del contrato", e);
            }
            throw e;
        }
    }

    private DeployedContract saveDeployedContract(ContractName contractName, String contractAddress) {
        try {
            DeployedContract deployed = new DeployedContract(
                    contractName,
                    new ContractAddress(contractAddress),
                    new DeployedAt(LocalDateTime.now())
            );
            return contractRepository.saveAndFlush(deployed);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el contrato en la base de datos", e);
        }
    }



    @Override
    public TransactionReceipt signStage(SignStageCommand command) throws Exception {
        // 1. Buscar el contrato desplegado
        DeployedContract deployed = contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc("SmartContractVinificationProcess")
                .orElseThrow(() -> new RuntimeException("No deployed contract found"));

        // 2. Cargar el contrato
        SmartContractVinificationProcess contract = SmartContractVinificationProcess.load(
                deployed.getContractAddress().getContractAddress(),
                web3j,
                credentials,
                new DefaultGasProvider() {
                    @Override
                    public BigInteger getGasLimit() { return gasLimit; }
                    @Override
                    public BigInteger getGasPrice() { return gasPrice; }
                }
        );

        // 3. Conversión de datos
        BigInteger batchIdBigInt = BigInteger.valueOf(command.batchId());
        BigInteger stageIdBigInt = BigInteger.valueOf(command.stageId());
        BigInteger startDateBigInt = BigInteger.valueOf(command.startDate());
        BigInteger endDateBigInt = BigInteger.valueOf(command.endDate());

        // 4. Llamar al contrato
        TransactionReceipt receipt = contract.signStage(
                batchIdBigInt,
                stageIdBigInt,
                command.stageName(),
                startDateBigInt,
                endDateBigInt,
                command.dataHash()
        ).send();

        if (!receipt.isStatusOK()) {
            throw new RuntimeException("Error al firmar la etapa: " + receipt.getTransactionHash());
        }

        return receipt;
    }
}

