package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.receptionstage.ReceptionStageNotBeCreated;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateEmptyReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.DeleteReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetReceptionStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage.ReceptionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage.ReceptionStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage.ProcessStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.CreateEmptyReceptionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.CreateReceptionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.ReceptionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.UpdateReceptionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage.CreateEmptyReceptionStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage.CreateReceptionStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage.ReceptionStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage.UpdateReceptionStageCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/batches/{batchId}", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reception Stage", description = "Reception Stage Management Endpoints")
public class ReceptionStageController {
    private final ReceptionStageCommandService commandService;
    private final ReceptionStageQueryService queryService;

    public ReceptionStageController(ReceptionStageCommandService commandService, ReceptionStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/reception-stage */
    @Operation(
            summary = "Get Reception Stage by Wine Batch ID",
            description = "Recupera la etapa de recepción asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de recepción.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **receptionStageId**: es el ID de la fase de recepción.\n" +
                    "- **batchId**: es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee**: es el encargado del registro de los datos.\n" +
                    "- **Sugar Level**: es el nivel de azúcar (Brix).\n" +
                    "- **pH Level**: es el nivel de pH de la muestra.\n" +
                    "- **Temperature**: es la temperatura de la recepción.\n" +
                    "- **Quantity (kg)**: es la cantidad de uvas en kilogramos.\n" +
                    "- **Comment**: son los comentarios adicionales sobre la recepción.\n" +
                    "- **Completion Status**: indica si la fase ha sido completada o no.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de recepción", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de recepción fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReceptionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de recepción",
                                    summary = "Respuesta exitosa con los detalles de la etapa de recepción",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "sugarLevel": 22.5,
                                      "pHLevel": 3.5,
                                      "temperature": 18.0,
                                      "quantityKg": 3200,
                                      "comment": "Recepción de uvas en buen estado.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "RECEPTION",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de recepción no fue encontrada."
            )
    })
    @GetMapping("/reception-stage")
    public ResponseEntity<ReceptionStageResource> getReceptionStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetReceptionStageByBatchIdQuery(batchId);
        var receptionStage = queryService.getReceptionStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de recepción para el lote de vino especificado."));

        var receptionStageResource = ReceptionStageResourceAssembler.toResource(receptionStage);
        return ResponseEntity.ok(receptionStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/reception-stage */
    @Operation(
            summary = "Create a new Reception Stage",
            description = "Crea una nueva etapa de recepción para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de recepción.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId**: es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee**: es el encargado del registro de los datos.\n" +
                    "- **Sugar Level**: es el nivel de azúcar (Brix).\n" +
                    "- **pH Level**: es el nivel de pH de la muestra.\n" +
                    "- **Temperature**: es la temperatura de la recepción.\n" +
                    "- **Quantity (kg)**: es la cantidad de uvas en kilogramos.\n" +
                    "- **Comment**: son los comentarios adicionales sobre la recepción.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de recepción", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de recepción fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReceptionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de recepción creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "sugarLevel": 22.5,
                                      "pHLevel": 3.5,
                                      "temperature": 18.0,
                                      "quantityKg": 3200,
                                      "comment": "Recepción de uvas en buen estado.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "RECEPTION",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de recepción no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping("/reception-stage")
    public ResponseEntity<ReceptionStageResource> addReceptionStageByBatch(@RequestBody @Valid CreateReceptionStageResource resource, @PathVariable Long batchId) {
        var command = CreateReceptionStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var receptionStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de recepción."));

        var receptionStageResource = ReceptionStageResourceAssembler.toResource(receptionStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(receptionStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/empty/reception-stage*/
    @Operation(
            summary = "Create an empty Reception Stage for a Wine Batch ID",
            description = "Crea una nueva etapa de recepción con todos los campos vacíos. Este endpoint no requiere ninguno de los campos para registrar correctamente la etapa de recepción.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId**: es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee**: es el encargado del registro de los datos.\n" +
                    "- **Sugar Level**: es el nivel de azúcar (Brix).\n" +
                    "- **pH Level**: es el nivel de pH de la muestra.\n" +
                    "- **Temperature**: es la temperatura de la recepción.\n" +
                    "- **Quantity (kg)**: es la cantidad de uvas en kilogramos.\n" +
                    "- **Comment**: son los comentarios adicionales sobre la recepción.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de recepción", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de recepción fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReceptionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de recepción creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 3,
                                      "batchId": 7,
                                      "employee": null,
                                      "startDate": null,
                                      "endDate": null,
                                      "sugarLevel": null,
                                      "pHLevel": null,
                                      "temperature": null,
                                      "quantityKg": null,
                                      "comment": null,
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "RECEPTION",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("/empty/reception-stage")
    public ResponseEntity<ReceptionStageResource> createEmptyReceptionStage(
            @RequestBody @Valid CreateEmptyReceptionStageResource resource,
            @PathVariable Long batchId
    ) {
        var command = CreateEmptyReceptionStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var receptionStage = commandService.handle(command)
                .orElseThrow(() -> new ReceptionStageNotBeCreated("No se pudo agregar la etapa de recepción con los campos vacíos."));

        var receptionStageResource = ReceptionStageResourceAssembler.toResource(receptionStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(receptionStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/reception-stage */
    @Operation(
            summary = "Update Reception Stage",
            description = "Actualiza parcialmente una etapa de recepción existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **receptionStageId**: es el ID de la fase de recepción.\n" +
                    "- **batchId**: es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee**: es el encargado del registro de los datos.\n" +
                    "- **Sugar Level**: es el nivel de azúcar (Brix).\n" +
                    "- **pH Level**: es el nivel de pH de la muestra.\n" +
                    "- **Temperature**: es la temperatura de la recepción.\n" +
                    "- **Quantity (kg)**: es la cantidad de uvas en kilogramos.\n" +
                    "- **Comment**: son los comentarios adicionales sobre la recepción.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de recepción", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de recepción fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReceptionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de recepción actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "sugarLevel": 23.0,
                                      "pHLevel": 3.4,
                                      "temperature": 19.0,
                                      "quantityKg": 3300,
                                      "comment": "Recepción de uvas en excelente estado.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "RECEPTION",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de recepción no fue encontrada."
            )
    })
    @PatchMapping("/reception-stage")
    public ResponseEntity<ReceptionStageResource> updateReceptionStage(@PathVariable Long batchId, @RequestBody @Valid UpdateReceptionStageResource resource) {
        var command = UpdateReceptionStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedReceptionStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de recepción para actualizar."));

        var receptionStageResource = ReceptionStageResourceAssembler.toResource(updatedReceptionStage);
        return ResponseEntity.ok(receptionStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/reception-stage */
    @Operation(
            summary = "Delete Reception Stage",
            description = "Elimina una etapa de recepción por su ID. Esta operación eliminará permanentemente la etapa de recepción del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de recepción", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de recepción fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de recepción no fue encontrada."
            )
    })
    @DeleteMapping("/reception-stage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceptionStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteReceptionStageCommand(batchId));
    }
}
