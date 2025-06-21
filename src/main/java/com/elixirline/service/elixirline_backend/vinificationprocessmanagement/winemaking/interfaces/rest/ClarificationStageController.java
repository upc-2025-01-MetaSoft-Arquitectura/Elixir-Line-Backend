package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.DeleteClarificationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetClarificationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage.ClarificationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage.ClarificationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.ClarificationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.CreateClarificationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.CreateEmptyClarificationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.UpdateClarificationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage.ClarificationStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage.CreateClarificationStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage.CreateEmptyClarificationStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage.UpdateClarificationStageCommandFromResourceAssembler;
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
@Tag(name = "Clarification Stage", description = "Clarification Stage Management Endpoints")
public class ClarificationStageController {
    private final ClarificationStageCommandService commandService;
    private final ClarificationStageQueryService queryService;

    public ClarificationStageController(ClarificationStageCommandService commandService, ClarificationStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/clarification-stage */
    @Operation(
            summary = "Get Clarification Stage by Wine Batch ID",
            description = "Recupera la etapa de clarificación asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de clarificación.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **clarificationStageId** (Long): es el ID de la etapa de clarificación.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la clarificación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la clarificación.\n" +
                    "- **Method Used** (String): es el método de clarificación utilizado.\n" +
                    "- **Initial Turbidity** (Double): es la turbidez inicial en NTU.\n" +
                    "- **Final Turbidity** (Double): es la turbidez final en NTU.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Temperature** (Double): es la temperatura durante la clarificación.\n" +
                    "- **Duration** (Integer): es la duración de la clarificación en horas.\n" +
                    "- **Clarifying Agents** (Map<String, Double>): son los agentes clarificantes utilizados, donde cada agente tiene una dosis en g/hL.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la clarificación.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es CLARIFICATION.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de clarificación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de clarificación fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClarificationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de clarificación",
                                    summary = "Respuesta exitosa con los detalles de la etapa de clarificación",
                                    value = """
                                    {
                                      "clarificationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "methodUsed": "Centrifugation",
                                      "initialTurbidity": 10.0,
                                      "finalTurbidity": 1.0,
                                      "volume": 1000.0,
                                      "temperature": 20.0,
                                      "duration": 2,
                                      "clarifyingAgents": {
                                        "Bentonite": 5.0,
                                        "Gelatin": 2.0
                                      },
                                      "comment": "Clarificación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CLARIFICATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de clarificación no fue encontrada."
            )
    })
    @GetMapping("/clarification-stage")
    public ResponseEntity<ClarificationStageResource> getClarificationStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetClarificationStageByBatchIdQuery(batchId);
        var clarificationStage = queryService.getClarificationStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de clarificación para el lote de vino especificado."));

        var clarificationStageResource = ClarificationStageResourceAssembler.toResource(clarificationStage);
        return ResponseEntity.ok(clarificationStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/clarification-stage */
    @Operation(
            summary = "Create a new Clarification Stage",
            description = "Crea una nueva etapa de clarificación para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de clarificación.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la clarificación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la clarificación.\n" +
                    "- **Method Used** (String): es el método de clarificación utilizado.\n" +
                    "- **Initial Turbidity** (Double): es la turbidez inicial en NTU.\n" +
                    "- **Final Turbidity** (Double): es la turbidez final en NTU.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Temperature** (Double): es la temperatura durante la clarificación.\n" +
                    "- **Duration** (Integer): es la duración de la clarificación en horas.\n" +
                    "- **Clarifying Agents** (Map<String, Double>): son los agentes clarificantes utilizados, donde cada agente tiene una dosis en g/hL.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la clarificación.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de clarificación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de clarificación fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClarificationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de clarificación creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "clarificationStageId": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "methodUsed": "Centrifugation",
                                      "initialTurbidity": 10.0,
                                      "finalTurbidity": 1.0,
                                      "volume": 1000.0,
                                      "temperature": 20.0,
                                      "duration": 2,
                                      "clarifyingAgents": {
                                        "Bentonite": 5.0,
                                        "Gelatin": 2.0
                                      },
                                      "comment": "Clarificación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CLARIFICATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de clarificación no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping("/clarification-stage")
    public ResponseEntity<ClarificationStageResource> addClarificationStageByBatch(@RequestBody @Valid CreateClarificationStageResource resource, @PathVariable Long batchId) {
        var command = CreateClarificationStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var clarificationStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de clarificación."));

        var clarificationStageResource = ClarificationStageResourceAssembler.toResource(clarificationStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(clarificationStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/empty/clarification-stage */
    @Operation(
            summary = "Create an empty Clarification Stage for a Wine Batch ID",
            description = "Crea una nueva etapa de correción con todos los campos vacíos. Este endpoint no requiere ninguno de los campos para registrar correctamente la etapa de correción.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la clarificación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la clarificación.\n" +
                    "- **Method Used** (String): es el método de clarificación utilizado.\n" +
                    "- **Initial Turbidity** (Double): es la turbidez inicial en NTU.\n" +
                    "- **Final Turbidity** (Double): es la turbidez final en NTU.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Temperature** (Double): es la temperatura durante la clarificación.\n" +
                    "- **Duration** (Integer): es la duración de la clarificación en horas.\n" +
                    "- **Clarifying Agents** (Map<String, Double>): son los agentes clarificantes utilizados, donde cada agente tiene una dosis en g/hL.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la clarificación.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de clarificación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de clarificación fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClarificationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de clarificación creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "clarificationStageId": 2,
                                      "batchId": 10,
                                      "employee": null,
                                      "startDate": null,
                                      "endDate": null,
                                      "methodUsed": null,
                                      "initialTurbidity": null,
                                      "finalTurbidity": null,
                                      "volume": null,
                                      "temperature": null,
                                      "duration": null,
                                      "clarifyingAgents": { },
                                      "comment": null,
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CLARIFICATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("empty/clarification-stage")
    public ResponseEntity<ClarificationStageResource> addEmptyClarificationStageByBatch(@RequestBody @Valid CreateEmptyClarificationStageResource resource, @PathVariable Long batchId) {
        var command = CreateEmptyClarificationStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var clarificationStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de clarificación."));

        var clarificationStageResource = ClarificationStageResourceAssembler.toResource(clarificationStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(clarificationStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/clarification-stage */
    @Operation(
            summary = "Update Clarification Stage",
            description = "Actualiza parcialmente una etapa de clarificación existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **clarificationStageId** (Long): es el ID de la etapa de clarificación.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la clarificación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la clarificación.\n" +
                    "- **Method Used** (String): es el método de clarificación utilizado.\n" +
                    "- **Initial Turbidity** (Double): es la turbidez inicial en NTU.\n" +
                    "- **Final Turbidity** (Double): es la turbidez final en NTU.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Temperature** (Double): es la temperatura durante la clarificación.\n" +
                    "- **Duration** (Integer): es la duración de la clarificación en horas.\n" +
                    "- **Clarifying Agents** (Map<String, Double>): son los agentes clarificantes utilizados, donde cada agente tiene una dosis en g/hL.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la clarificación.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de clarificación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de clarificación fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClarificationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de clarificación actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "clarificationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "methodUsed": "Centrifugation",
                                      "initialTurbidity": 10.0,
                                      "finalTurbidity": 1.0,
                                      "volume": 1000.0,
                                      "temperature": 20.0,
                                      "duration": 2,
                                      "clarifyingAgents": {
                                        "Bentonite": 5.0,
                                        "Gelatin": 2.0
                                      },
                                      "comment": "Clarificación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CLARIFICATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de clarificación no fue encontrada."
            )
    })
    @PatchMapping("/clarification-stage")
    public ResponseEntity<ClarificationStageResource> updateClarificationStage(@PathVariable Long batchId, @RequestBody @Valid UpdateClarificationStageResource resource) {
        var command = UpdateClarificationStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedClarificationStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de clarificación para actualizar."));

        var clarificationStageResource = ClarificationStageResourceAssembler.toResource(updatedClarificationStage);
        return ResponseEntity.ok(clarificationStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/clarification-stage */
    @Operation(
            summary = "Delete Clarification Stage",
            description = "Elimina una etapa de clarificación por su ID. Esta operación eliminará permanentemente la etapa de clarificación del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de clarificación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de clarificación fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de clarificación no fue encontrada."
            )
    })
    @DeleteMapping("/clarification-stage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClarificationStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteClarificationStageByBatchCommand(batchId));
    }
}

