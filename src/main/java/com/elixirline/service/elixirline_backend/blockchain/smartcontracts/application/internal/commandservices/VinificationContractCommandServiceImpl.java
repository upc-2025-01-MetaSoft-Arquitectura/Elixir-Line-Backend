package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.BatchIdAndStageNameAlreadyRegistered;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.DeployedContractNotFound;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.TransactionNotBeCreated;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.entities.generated.SmartContractVinificationProcess;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.BlockNumber;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractAddress;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractName;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.DeployedAt;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessCommandService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinificationContractCommandServiceImpl implements VinificationProcessCommandService {
    private final Web3j web3j;
    private final Credentials credentials;
    private final DeployedContractRepository contractRepository;
    private final VinificationProcessQueryService vinificationProcessQueryService;
    private static Logger log = LoggerFactory.getLogger(VinificationContractCommandServiceImpl.class);

    @Value("${web3.gas.limit}")
    private BigInteger gasLimit;

    @Value("${web3.gas.price}")
    private BigInteger gasPrice;


    @Override
    @Transactional
    public DeployedContract deployContract(DeployContractCommand command) throws Exception {
        BigInteger balance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance();
        log.info("Saldo en Sepolia: {} ETH", Convert.fromWei(balance.toString(), Convert.Unit.ETHER));
        if (balance.compareTo(BigInteger.valueOf(5_000_000_000_000_000L)) < 0) { // 0.005 ETH mínimo
            throw new RuntimeException("Saldo insuficiente para desplegar contrato");
        }



        final String CONTRACT_NAME = "SmartContractVinificationProcess";
        ContractName contractName = new ContractName(CONTRACT_NAME);

        // 1. Verificar si ya existe un contrato desplegado
        /*
        Optional<DeployedContract> existing = contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc(CONTRACT_NAME);

        if (existing.isPresent()) {
            log.info("Usando contrato existente: {}", existing.get().getContractAddress());
            return existing.get();
        }*/

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
                    new DefaultGasProvider()
            ).send();

            if (contract == null || contract.getContractAddress() == null) {
                throw new RuntimeException("El contrato no generó una dirección válida");
            }

            // Obtener el receipt para conocer el bloque (blockNumber)
            TransactionReceipt receipt = contract.getTransactionReceipt().orElseThrow(() -> new RuntimeException("No se obtuvo el recibo de la transacción"));
            BigInteger blockNumber = receipt.getBlockNumber();
            log.info("Contrato desplegado exitosamente en: {} en bloque {}", contract.getContractAddress(), blockNumber);

            return saveDeployedContract(contractName, contract.getContractAddress(), blockNumber);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("invalid opcode")) {
                throw new RuntimeException("No se pudo verificar el despliegue del contrato", e);
            }
            throw e;
        }
    }

    private DeployedContract saveDeployedContract(ContractName contractName, String contractAddress, BigInteger blockNumber) {
        try {
            DeployedContract deployed = new DeployedContract(
                    contractName,
                    new ContractAddress(contractAddress),
                    new DeployedAt(LocalDateTime.now()),
                    new BlockNumber(blockNumber)
            );
            return contractRepository.saveAndFlush(deployed);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el contrato en la base de datos", e);
        }
    }

    @Override
    public TransactionReceipt signStage(SignStageCommand command) throws Exception {
        GetStageIsSigned isSigned = new GetStageIsSigned(command.batchId(), command.stageName());

        if (vinificationProcessQueryService.isStageSigned(isSigned)) {
            throw new BatchIdAndStageNameAlreadyRegistered(
                    "Sign smart contract",
                    "El SmartContract ya fue firmado para la etapa " + command.stageName() + " del lote " + command.batchId(),
                    new RuntimeException("The Signed SmartContract already exists")
            );
        }

        // 1: Buscar el SmartContract desplegado
        DeployedContract deployed = contractRepository
                .findTopByContractNameContractNameOrderByDeployedAtDesc("SmartContractVinificationProcess")
                .orElseThrow(() -> new DeployedContractNotFound(
                        "Find deployed contract",
                        "Failed to find deployed contract with name: " + "SmartContractVinificationProcess",
                        new RuntimeException("No deployed contract found")
                ));

        // 2: Cargar el SmartContract
        SmartContractVinificationProcess contract = SmartContractVinificationProcess.load(
                deployed.getContractAddress().getContractAddress(),
                web3j,
                credentials,
                new DefaultGasProvider()
        );


        // 3: Convertir datos, llamar al SmartContract y firmar
        try {
            return contract.signStage(
                    BigInteger.valueOf(command.batchId()),
                    BigInteger.valueOf(command.stageId()),
                    command.stageName(),
                    BigInteger.valueOf(command.getStartDateEpochSeconds()),
                    BigInteger.valueOf(command.getEndDateEpochSeconds()),
                    command.dataHash()
            ).send();
        } catch (ContractCallException e) {
            throw new TransactionNotBeCreated(
                    "signStage",
                    "Contract reverted. Message: " + e.getMessage(),
                    e
            );
        } catch (Exception e) {
            throw new TransactionNotBeCreated(
                    "signStage",
                    "Unexpected error while signing stage with batchId: " + command.batchId() + " and stageName: " + command.stageName(),
                    e
            );
        }
    }
}

