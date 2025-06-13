package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.BatchNotBeCreated;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.DeleteBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.BatchCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.BatchQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.BatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.CreateBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.PatchBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.UpdateBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.BatchResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.CreateBatchCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.PatchBatchCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.UpdateBatchCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/batches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Batches", description = "Batch Management Endpoints")

public class WineMakingProcessController {
    private final BatchCommandService commandService;
    private final BatchQueryService queryService;

    public WineMakingProcessController(BatchCommandService commandService, BatchQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(
            summary = "Get all batches",
            description = "Retrieve a list of all batches in the system. This endpoint returns an array of batch resources."
    )
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successful operation",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = BatchResource.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500", description = "Internal server error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorHandler.class)
                        )
                )
            }
    )
    @GetMapping
    public ResponseEntity<List<BatchResource>> getAll() {
        List<BatchVineyard> batches = queryService.handle(new GetAllBatchesQuery());
        List<BatchResource> resources = batches.stream()
                .map(BatchResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Get batch by ID",
            description = "Retrieve a batch by its ID. Provide the unique identifier of the batch to get its details."
    )
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Batch found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = BatchResource.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Batch not found",
                        content = @Content(
                                schema = @Schema(implementation = ErrorHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal server error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorHandler.class)
                        )
                )
            }
    )
    @GetMapping("/{batchId}")
    public ResponseEntity<BatchResource> getById(@Parameter(description = "ID of the batch to retrieve", required = true)
                                                 @PathVariable Long batchId) {
        return queryService.handle(new GetBatchByIdQuery(batchId))
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new batch",
            description = "Create a new batch with the provided details. This endpoint requires all necessary fields to create a batch."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Batch created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<BatchResource> createBatch(@RequestBody @Valid CreateBatchResource resource) {
        CreateBatchCommand command = CreateBatchCommandFromResourceAssembler.toCommandFromResource(resource);
        BatchVineyard batch = commandService.handle(command)
                .orElseThrow(() -> new BatchNotBeCreated("Batch Command Service Error"));
        BatchResource batchResource = BatchResourceAssembler.toResource(batch);
        return ResponseEntity.status(HttpStatus.CREATED).body(batchResource);
    }

    @Operation(
            summary = "Update a batch",
            description = "Update an existing batch by its ID. Only the specified fields will be updated."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Batch updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Batch not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            )
    })
    @PutMapping("/{batchId}")
    public ResponseEntity<BatchResource> update(@Parameter(description = "ID of the batch to update", required = true)
                                                @PathVariable Long batchId,
                                                @RequestBody @Valid UpdateBatchResource resource) {
        var command = UpdateBatchCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedBatch = commandService.update(command);
        return updatedBatch
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a batch",
            description = "Delete a batch by its ID. This operation will permanently remove the batch from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Batch deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Batch not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            )
    })
    @DeleteMapping("/{batchId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBatch(@Parameter(description = "ID of the batch to delete", required = true)
                            @PathVariable Long batchId) {
        commandService.delete(new DeleteBatchCommand(batchId));
    }

    @Operation(
            summary = "Patch a batch",
            description = "Partially update a batch by its ID. Only the specified fields will be updated."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Batch updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Batch not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorHandler.class)
                    )
            )
    })
    @PatchMapping("/{batchId}")
    public ResponseEntity<BatchResource> patch(@PathVariable Long batchId, @RequestBody @Valid PatchBatchResource resource) {
        var command = PatchBatchCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        commandService.patch(command);
        return queryService.handle(new GetBatchByIdQuery(batchId))
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
