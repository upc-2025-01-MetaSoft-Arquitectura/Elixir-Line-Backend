package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.DeletePressingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetPressingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage.PressingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage.PressingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.CreateEmptyPressingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.CreatePressingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.PressingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.UpdatePressingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage.CreateEmptyPressingStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage.CreatePressingStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage.PressingStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage.UpdatePressingStageCommandFromResourceAssembler;
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
@Tag(name = "Pressing Stage", description = "Pressing Stage Management Endpoints")
public class PressingStageController {
    private final PressingStageCommandService commandService;
    private final PressingStageQueryService queryService;

    public PressingStageController(PressingStageCommandService commandService, PressingStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/pressing-stage */
    @Operation(
            summary = "Get Pressing Stage by Wine Batch ID",
            description = "Recupera la etapa de prensado asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de prensado.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **pressingStageId** (Long): es el ID de la etapa de prensado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del prensado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del prensado.\n" +
                    "- **Press Type** (String): es el tipo de prensa utilizada.\n" +
                    "- **Press Pressure** (Double): es la presión de la prensa en bares.\n" +
                    "- **Pressing Duration** (Integer): es la duración del prensado en minutos.\n" +
                    "- **Pomade Weight** (Double): es el peso del orujo en kilogramos.\n" +
                    "- **Yield** (Double): es el rendimiento en litros del mosto.\n" +
                    "- **Must Usage** (String): es el uso del mosto en el proceso.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el prensado.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es PRESSING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de prensado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de prensado fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PressingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de prensado",
                                    summary = "Respuesta exitosa con los detalles de la etapa de prensado",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "pressType": "Hydraulic",
                                      "pressure": 2.5,
                                      "duration": 120,
                                      "pomadeWeight": 1500.0,
                                      "yield": 800.0,
                                      "mustUsage": "Fermentation",
                                      "comment": "Prensado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "PRESSING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de prensado no fue encontrada."
            )
    })
    @GetMapping("/pressing-stage")
    public ResponseEntity<PressingStageResource> getPressingStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetPressingStageByBatchIdQuery(batchId);
        var pressingStage = queryService.getPressingStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de prensado para el lote de vino especificado."));

        var pressingStageResource = PressingStageResourceAssembler.toResource(pressingStage);
        return ResponseEntity.ok(pressingStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/pressing-stage */
    @Operation(
            summary = "Create a new Pressing Stage",
            description = "Crea una nueva etapa de prensado para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de prensado.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **pressingStageId** (Long): es el ID de la etapa de prensado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del prensado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del prensado.\n" +
                    "- **Press Type** (String): es el tipo de prensa utilizada.\n" +
                    "- **Press Pressure** (Double): es la presión de la prensa en bares.\n" +
                    "- **Pressing Duration** (Integer): es la duración del prensado en minutos.\n" +
                    "- **Pomade Weight** (Double): es el peso del orujo en kilogramos.\n" +
                    "- **Yield** (Double): es el rendimiento en litros del mosto.\n" +
                    "- **Must Usage** (String): es el uso del mosto en el proceso.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el prensado.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es PRESSING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de prensado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de prensado fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PressingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de prensado creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "pressType": "Hydraulic",
                                      "pressure": 2.5,
                                      "duration": 120,
                                      "pomadeWeight": 1500.0,
                                      "yield": 800.0,
                                      "mustUsage": "Fermentation",
                                      "comment": "Prensado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "PRESSING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de prensado no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping("/pressing-stage")
    public ResponseEntity<PressingStageResource> addPressingStageByBatch(@RequestBody @Valid CreatePressingStageResource resource, @PathVariable Long batchId) {
        var command = CreatePressingStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var pressingStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de prensado."));

        var pressingStageResource = PressingStageResourceAssembler.toResource(pressingStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(pressingStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/empty/pressing-stage */
    @Operation(
            summary = "Create an empty Pressing Stage for a Wine Batch ID",
            description = "Crea una nueva etapa de prensado con todos los campos vacíos. Este endpoint no requiere ninguno de los campos para registrar correctamente la etapa de prensado.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **pressingStageId** (Long): es el ID de la etapa de prensado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del prensado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del prensado.\n" +
                    "- **Press Type** (String): es el tipo de prensa utilizada.\n" +
                    "- **Press Pressure** (Double): es la presión de la prensa en bares.\n" +
                    "- **Pressing Duration** (Integer): es la duración del prensado en minutos.\n" +
                    "- **Pomade Weight** (Double): es el peso del orujo en kilogramos.\n" +
                    "- **Yield** (Double): es el rendimiento en litros del mosto.\n" +
                    "- **Must Usage** (String): es el uso del mosto en el proceso.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el prensado.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es PRESSING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de prensado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de prensado fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PressingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de prensado creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 2,
                                      "batchId": 10,
                                      "employee": null,
                                      "startDate": null,
                                      "endDate": null
                                      "pressType": null,
                                      "pressure": null,
                                      "duration": null,
                                      "pomadeWeight": null,
                                      "yield": null,
                                      "mustUsage": null,
                                      "comment": null,
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "PRESSING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("/empty/pressing-stage")
    public ResponseEntity<PressingStageResource> addEmptyPressingStageByBatch(@RequestBody @Valid CreateEmptyPressingStageResource resource, @PathVariable Long batchId) {
        var command = CreateEmptyPressingStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var pressingStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de prensado."));

        var pressingStageResource = PressingStageResourceAssembler.toResource(pressingStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(pressingStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/pressing-stage */
    @Operation(
            summary = "Update Pressing Stage",
            description = "Actualiza parcialmente una etapa de prensado existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **pressingStageId** (Long): es el ID de la etapa de prensado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del prensado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del prensado.\n" +
                    "- **Press Type** (String): es el tipo de prensa utilizada.\n" +
                    "- **Press Pressure** (Double): es la presión de la prensa en bares.\n" +
                    "- **Pressing Duration** (Integer): es la duración del prensado en minutos.\n" +
                    "- **Pomade Weight** (Double): es el peso del orujo en kilogramos.\n" +
                    "- **Yield** (Double): es el rendimiento en litros del mosto.\n" +
                    "- **Must Usage** (String): es el uso del mosto en el proceso.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el prensado.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es PRESSING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de prensado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de prensado fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PressingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de prensado actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "pressType": "Hydraulic",
                                      "pressure": 2.5,
                                      "duration": 120,
                                      "pomadeWeight": 1500.0,
                                      "yield": 800.0,
                                      "mustUsage": "Fermentation",
                                      "comment": "Prensado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "PRESSING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de prensado no fue encontrada."
            )
    })
    @PatchMapping("/pressing-stage")
    public ResponseEntity<PressingStageResource> updatePressingStage(@PathVariable Long batchId, @RequestBody @Valid UpdatePressingStageResource resource) {
        var command = UpdatePressingStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedPressingStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de prensado para actualizar."));

        var pressingStageResource = PressingStageResourceAssembler.toResource(updatedPressingStage);
        return ResponseEntity.ok(pressingStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/pressing-stage */
    @Operation(
            summary = "Delete Pressing Stage",
            description = "Elimina una etapa de prensado por su ID. Esta operación eliminará permanentemente la etapa de prensado del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de prensado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de prensado fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de prensado no fue encontrada."
            )
    })
    @DeleteMapping("/pressing-stage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePressingStage(@PathVariable Long batchId) {
        commandService.delete(new DeletePressingStageByBatchCommand(batchId));
    }
}

