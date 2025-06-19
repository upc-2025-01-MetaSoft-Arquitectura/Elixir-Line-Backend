package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.DeleteBottlingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetBottlingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage.BottlingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage.BottlingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.BottlingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.CreateBottlingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.UpdateBottlingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage.BottlingStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage.CreateBottlingStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage.UpdateBottlingStageCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/batches/{batchId}/bottling-stage", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bottling Stage", description = "Bottling Stage Management Endpoints")
public class BottlingStageController {
    private final BottlingStageCommandService commandService;
    private final BottlingStageQueryService queryService;

    public BottlingStageController(BottlingStageCommandService commandService, BottlingStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/bottling-stage */
    @Operation(
            summary = "Get Bottling Stage by Wine Batch ID",
            description = "Recupera la etapa de embotellado asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de embotellado.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **bottlingStageId** (Long): es el ID de la etapa de embotellado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del embotellado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del embotellado.\n" +
                    "- **Bottling Line** (String): es la línea de embotellado utilizada.\n" +
                    "- **Filled Bottles** (Integer): es la cantidad de botellas llenadas.\n" +
                    "- **Bottle Volume** (Double): es el volumen de cada botella en ml.\n" +
                    "- **Total Volume** (Double): es el volumen total embotellado en litros.\n" +
                    "- **Sealing Type** (String): es el tipo de sellado utilizado.\n" +
                    "- **Vineyard Code** (String): es el código de viñedo asociado al lote.\n" +
                    "- **Temperature** (Double): es la temperatura durante el embotellado.\n" +
                    "- **Filtered Before Bottling** (Boolean): indica si el vino fue filtrado antes del embotellado.\n" +
                    "- **Labels At This Stage** (Boolean): indica si se aplicaron etiquetas en esta etapa.\n" +
                    "- **Capsule or Seal Application** (Boolean): indica si se aplicaron cápsulas o precintos.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el embotellado.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es BOTTLING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de embotellado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de embotellado fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BottlingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de embotellado",
                                    summary = "Respuesta exitosa con los detalles de la etapa de embotellado",
                                    value = """
                                    {
                                      "bottlingStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "bottlingLine": "Line 1",
                                      "filledBottles": 500,
                                      "bottleVolume": 750.0,
                                      "totalVolume": 375.0,
                                      "sealingType": "Screw Cap",
                                      "vineyardCode": "VINEYARD-01",
                                      "temperature": 20.0,
                                      "filteredBeforeBottling": true,
                                      "labelsAtThisStage": true,
                                      "capsuleOrSealApplication": true,
                                      "comment": "Embotellado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "BOTTLING",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de embotellado no fue encontrada."
            )
    })
    @GetMapping
    public ResponseEntity<BottlingStageResource> getBottlingStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetBottlingStageByBatchIdQuery(batchId);
        var bottlingStage = queryService.getBottlingStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de embotellado para el lote de vino especificado."));

        var bottlingStageResource = BottlingStageResourceAssembler.toResource(bottlingStage);
        return ResponseEntity.ok(bottlingStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/bottling-stage */
    @Operation(
            summary = "Create a new Bottling Stage",
            description = "Crea una nueva etapa de embotellado para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de embotellado.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del embotellado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del embotellado.\n" +
                    "- **Bottling Line** (String): es la línea de embotellado utilizada.\n" +
                    "- **Filled Bottles** (Integer): es la cantidad de botellas llenadas.\n" +
                    "- **Bottle Volume** (Double): es el volumen de cada botella en ml.\n" +
                    "- **Total Volume** (Double): es el volumen total embotellado en litros.\n" +
                    "- **Sealing Type** (String): es el tipo de sellado utilizado.\n" +
                    "- **Vineyard Code** (String): es el código de viñedo asociado al lote.\n" +
                    "- **Temperature** (Double): es la temperatura durante el embotellado.\n" +
                    "- **Filtered Before Bottling** (Boolean): indica si el vino fue filtrado antes del embotellado.\n" +
                    "- **Labels At This Stage** (Boolean): indica si se aplicaron etiquetas en esta etapa.\n" +
                    "- **Capsule or Seal Application** (Boolean): indica si se aplicaron cápsulas o precintos.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el embotellado.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de embotellado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de embotellado fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BottlingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de embotellado creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "bottlingStageId": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "bottlingLine": "Line 1",
                                      "filledBottles": 500,
                                      "bottleVolume": 750.0,
                                      "totalVolume": 375.0,
                                      "sealingType": "Screw Cap",
                                      "vineyardCode": "VINEYARD-01",
                                      "temperature": 20.0,
                                      "filteredBeforeBottling": true,
                                      "labelsAtThisStage": true,
                                      "capsuleOrSealApplication": true,
                                      "comment": "Embotellado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "BOTTLING",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de embotellado no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping
    public ResponseEntity<BottlingStageResource> addBottlingStageByBatch(@RequestBody @Valid CreateBottlingStageResource resource, @PathVariable Long batchId) {
        var command = CreateBottlingStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var bottlingStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de embotellado."));

        var bottlingStageResource = BottlingStageResourceAssembler.toResource(bottlingStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(bottlingStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/bottling-stage */
    @Operation(
            summary = "Update Bottling Stage",
            description = "Actualiza parcialmente una etapa de embotellado existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **bottlingStageId** (Long): es el ID de la etapa de embotellado.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del embotellado.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del embotellado.\n" +
                    "- **Bottling Line** (String): es la línea de embotellado utilizada.\n" +
                    "- **Filled Bottles** (Integer): es la cantidad de botellas llenadas.\n" +
                    "- **Bottle Volume** (Double): es el volumen de cada botella en ml.\n" +
                    "- **Total Volume** (Double): es el volumen total embotellado en litros.\n" +
                    "- **Sealing Type** (String): es el tipo de sellado utilizado.\n" +
                    "- **Vineyard Code** (String): es el código de viñedo asociado al lote.\n" +
                    "- **Temperature** (Double): es la temperatura durante el embotellado.\n" +
                    "- **Filtered Before Bottling** (Boolean): indica si el vino fue filtrado antes del embotellado.\n" +
                    "- **Labels At This Stage** (Boolean): indica si se aplicaron etiquetas en esta etapa.\n" +
                    "- **Capsule or Seal Application** (Boolean): indica si se aplicaron cápsulas o precintos.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el embotellado.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de embotellado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de embotellado fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BottlingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de embotellado actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "bottlingStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "bottlingLine": "Line 1",
                                      "filledBottles": 500,
                                      "bottleVolume": 750.0,
                                      "totalVolume": 375.0,
                                      "sealingType": "Screw Cap",
                                      "vineyardCode": "VINEYARD-01",
                                      "temperature": 20.0,
                                      "filteredBeforeBottling": true,
                                      "labelsAtThisStage": true,
                                      "capsuleOrSealApplication": true,
                                      "comment": "Embotellado realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "BOTTLING",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de embotellado no fue encontrada."
            )
    })
    @PatchMapping
    public ResponseEntity<BottlingStageResource> updateBottlingStage(@PathVariable Long batchId, @RequestBody @Valid UpdateBottlingStageResource resource) {
        var command = UpdateBottlingStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedBottlingStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de embotellado para actualizar."));

        var bottlingStageResource = BottlingStageResourceAssembler.toResource(updatedBottlingStage);
        return ResponseEntity.ok(bottlingStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/bottling-stage */
    @Operation(
            summary = "Delete Bottling Stage",
            description = "Elimina una etapa de embotellado por su ID. Esta operación eliminará permanentemente la etapa de embotellado del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de embotellado", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de embotellado fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de embotellado no fue encontrada."
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBottlingStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteBottlingStageByBatchCommand(batchId));
    }
}

