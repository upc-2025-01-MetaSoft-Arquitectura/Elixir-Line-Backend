package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses.*;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.DeployContractCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands.SignStageCommand;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageIsSigned;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessCommandService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services.VinificationProcessQueryService;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories.DeployedContractRepository;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.DeployedContractResource;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageDetailsResource;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources.StageResource;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.DeployedContractResourceAssembler;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.StageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.transform.StageDetailsResourceAssembler;
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
import java.time.Instant;
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
    @Operation(summary = "Deploy the smart contract", description = "Deploy a new instance of the smart contract")
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
    @Operation(summary = "Create a new stage signature", description = "Records a new stage signature on the blockchain")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stage successfully signed", content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/sign")
    public ResponseEntity<TransactionResponse> signStage(@Valid @RequestBody StageResource stageResource) {
        try {
            SignStageCommand command = StageCommandFromResourceAssembler.toCommandFromResource(stageResource);
            TransactionReceipt receipt = commandService.signStage(command);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new TransactionResponse(
                            receipt.getTransactionHash(),
                            "SUCCESS",
                            "Stage signed successfully",
                            Instant.now().toString()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new TransactionResponse(null, "FAILED", e.getMessage(), Instant.now().toString())
            );
        }
    }

    // GET: api/v1/blockchain/transactions/stage-signed
    @Operation(summary = "Check stage signature", description = "Returns true if the given stage is signed")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Signature verification result"),
            @ApiResponse(responseCode = "500", description = "Error checking signature")
    })
    @GetMapping("/{batchId}/{stageId}/stage-signed")
    public ResponseEntity<Boolean> isStageSigned(
            @PathVariable Long batchId,
            @PathVariable Long stageId
    ) throws Exception {
        return ResponseEntity.ok(queryService.isStageSigned(new GetStageIsSigned(batchId, stageId)));
    }

    // GET: api/v1/blockchain/transactions/stage-details
    @Operation(summary = "Get stage details", description = "Returns all blockchain details for a given stage")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stage details found", content = @Content(schema = @Schema(implementation = StageDetailsResource.class))),
            @ApiResponse(responseCode = "404", description = "Stage not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{batchId}/{stageId}/stage-details")
    public ResponseEntity<StageDetailsResource> getStageDetails(
            @PathVariable Long batchId,
            @PathVariable Long stageId
    ) throws Exception {
        var stage = queryService.getStageDetails(new GetStageDetails(batchId, stageId));
        return ResponseEntity.ok(StageDetailsResourceAssembler.toResource(stage));
    }
}

/*

@Operation(
        summary = "Create a new stage signature",
        description = "Records a new stage signature on the blockchain"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Stage successfully signed",
                content = @Content(schema = @Schema(implementation = TransactionResponse.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid request parameters",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
})
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<TransactionResponse> signStage(@Valid @RequestBody StageResource stageResource) {
    try {
        var command = StageCommandFromResourceAssembler.toCommandFromResource(stageResource);
        TransactionReceipt receipt = commandService.signStage(command);

        TransactionResponse response = new TransactionResponse(
                receipt.getTransactionHash(),
                "SUCCESS",
                "Transaction completed successfully",
                Instant.now().toString()
        );

        return ResponseEntity
                .created(URI.create("/api/v1/blockchain/transactions/" + receipt.getTransactionHash()))
                .body(response);

    } catch (IllegalArgumentException e) {
        log.error("Validation error: {}", e.getMessage(), e);
        throw new TransactionNotBeCreated(e.getMessage());
    } catch (Exception e) {
        log.error("Error signing stage: {}", e.getMessage(), e);
        throw new TransactionNotBeCreated("Internal server error");
    }
}




@Operation(
        summary = "Verify stage signature",
        description = "Checks if a stage has been signed on the blockchain"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Verification result",
                content = @Content(schema = @Schema(implementation = VerificationResponse.class))
        )
})
@GetMapping("/batch/{batchId}/stage/{stageId}/verify")
public ResponseEntity<VerificationResponse> verifyStageSignature(
        @Parameter(description = "Batch ID", required = true, example = "batch10")
        @PathVariable String batchId,

        @Parameter(description = "Stage ID", required = true, example = "stage54")
        @PathVariable String stageId
) {

    try {
        boolean isSigned = queryService.isStageSigned(new GetStageIsSigned(batchId, stageId));
        return ResponseEntity.ok(new VerificationResponse(isSigned, "Verification completed"));
    } catch (Exception e) {
        log.error("Error verifying stage: {}", e.getMessage(), e);
        throw new TransactionNotBeVerified(e.getMessage());
    }
}




@Operation(
        summary = "Get stage details",
        description = "Retrieves detailed information about a signed stage"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Stage details",
                content = @Content(schema = @Schema(implementation = StageDetailResponse.class))
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Stage not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
})
@GetMapping("/batch/{batchId}/stages/{stageId}")
public ResponseEntity<StageDetailResponse> getStageDetails(
        @Parameter(description = "Batch ID", required = true, example = "batch-123")
        @PathVariable String batchId,

        @Parameter(description = "Stage ID", required = true, example = "stage-456")
        @PathVariable String stageId
) {

    try {
        var stageDetails = queryService.getStageDetails(new GetStageDetails(batchId, stageId));
        if (stageDetails == null) {
            throw new TransactionDetailsNotBeRetrieved("Stage not found");
        }

        StageDetailResponse response = new StageDetailResponse(
                batchId,
                stageId,
                stageDetails.stageName,
                stageDetails.dataHash,
                stageDetails.completed,
                stageDetails.startDate.longValue(),
                stageDetails.endDate.longValue()
        );

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        log.error("Error retrieving stage details: {}", e.getMessage(), e);
        throw new TransactionDetailsNotBeRetrieved(e.getMessage());
    }
}



*/
