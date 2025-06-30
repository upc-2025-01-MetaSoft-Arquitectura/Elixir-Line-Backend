package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses.*;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessCommandService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.*;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.DeployedContractResourceAssembler;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.SignStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.SignStageResponseAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/api/v1/blockchain/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(
        name = "Blockchain Transactions",
        description = """
        ## Blockchain Transaction Management
        - Create and verify blockchain transactions
        - Track transaction status
        - Get transaction details
        """
)
public class SmartContractVinificationProcessController {
    private static final Logger log = LoggerFactory.getLogger(SmartContractVinificationProcessController.class);
    private final VinificationProcessCommandService commandService;
    private final VinificationProcessQueryService queryService;

    // POST: api/v1/blockchain/transactions/deploy
    @Operation(summary = "Deploy the smart contract", description = "Implementar una nueva instancia del contrato inteligente, solo se debe realizar una sola vez. En caso de que el código del Smart Contract cambie o se actualice, se deberá desplegar de nuevo el contrato con este endpoint.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contract deployed", content = @Content(schema = @Schema(implementation = DeployedContractResource.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/deploy")
    public ResponseEntity<?> deployContract() {
        try {
            DeployedContract deployed = commandService.deployContract(new DeployContractCommand());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(DeployedContractResourceAssembler.toResource(deployed));

        } catch (Exception e) {
            log.error("Error al desplegar contrato", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Error al desplegar el contrato",
                            "message", e.getMessage(),
                            "cause", e.getCause() != null ? e.getCause().getMessage() : "No hay causa adicional"
                    ));
        }
    }

    // POST: api/v1/blockchain/transactions/sign
    @Operation(summary = "Create a new stage signature", description = "Registra una nueva firma de etapa en la blockchain. Este endpoint devuelve el [transactionHash] que es el hash que asegura que una etapa se ha firmado correctamente en el Smart Contract.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stage successfully signed", content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/sign")
    public ResponseEntity<SignStageResponseResource> signStage(@Valid @RequestBody SignStageResource resource) throws Exception {
        SignStageCommand command = SignStageCommandFromResourceAssembler.toCommandFromResource(resource);
        TransactionReceipt receipt = commandService.signStage(command);
        SignStageResponseResource response = SignStageResponseAssembler.toResource(command, receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET: api/v1/blockchain/transactions/stage-signed
    @Operation(summary = "Check stage signature", description = "Devuelve verdadero si la etapa dada está firmada. Este endpoint necesita del batchId y del stageName o nombre de la etapa que se registraron al momento de firmar el Smart Contract con el endpoint de api/v1/blockchain/transactions/sign")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Signature verification result"),
            @ApiResponse(responseCode = "500", description = "Error checking signature")
    })
    @GetMapping("/{batchId}/{stageName}/stage-signed")
    public ResponseEntity<Boolean> isStageSigned(@PathVariable Long batchId, @PathVariable String stageName) throws Exception {
        boolean signed = queryService.isStageSigned(new GetStageIsSigned(batchId, stageName));
        return ResponseEntity.ok(signed);
    }

    // GET: api/v1/blockchain/transactions/stage-details
    @Operation(summary = "Get stage details", description = "Devuelve todos los detalles del Smart Contract para una etapa determinada. Este endpoint necesita del batchId y del stageName o nombre de la etapa que se registraron al momento de firmar el Smart Contract con el endpoint de api/v1/blockchain/transactions/sign")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stage details found", content = @Content(schema = @Schema(implementation = StageDetailsResource.class))),
            @ApiResponse(responseCode = "404", description = "Stage not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{batchId}/{stageName}/stage-details")
    public ResponseEntity<StageDetailsResource> getStageDetails(@PathVariable Long batchId, @PathVariable String stageName) throws Exception {
        return ResponseEntity.ok(queryService.getStageDetails(new GetStageDetails(batchId, stageName)));
    }
}
