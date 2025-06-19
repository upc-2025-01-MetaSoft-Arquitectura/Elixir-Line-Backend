package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.DeleteFermentationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFermentationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage.FermentationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage.FermentationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.CreateFermentationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.FermentationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.UpdateFermentationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage.CreateFermentationStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage.FermentationStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage.UpdateFermentationStageCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/batches/{batchId}/fermentation-stage", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Fermentation Stage", description = "Fermentation Stage Management Endpoints")
public class FermentationStageController {
    private final FermentationStageCommandService commandService;
    private final FermentationStageQueryService queryService;

    public FermentationStageController(FermentationStageCommandService commandService, FermentationStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/fermentation-stage */
    @Operation(
            summary = "Get Fermentation Stage by Wine Batch ID",
            description = "Recupera la etapa de fermentación asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de fermentación.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **fermentationStageId** (Long): es el ID de la etapa de fermentación.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la fermentación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la fermentación.\n" +
                    "- **Yeast Used** (Double): es la cantidad de levadura utilizada en mg/L.\n" +
                    "- **Fermentation Type** (String): es el tipo de fermentación utilizada.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la fermentación.\n" +
                    "- **Final pH** (Double): es el pH final de la fermentación.\n" +
                    "- **Max Temperature** (Double): es la temperatura máxima durante la fermentación.\n" +
                    "- **Min Temperature** (Double): es la temperatura mínima durante la fermentación.\n" +
                    "- **Tank Code** (String): es el código del tanque donde se realiza la fermentación.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la fermentación.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es FERMENTATION.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de fermentación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de fermentación fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FermentationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de fermentación",
                                    summary = "Respuesta exitosa con los detalles de la etapa de fermentación",
                                    value = """
                                    {
                                      "fermentationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "yeastUsed": 150.0,
                                      "fermentationType": "Alcoholic",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "maxTemperature": 30.0,
                                      "minTemperature": 15.0,
                                      "tankCode": "TANK-01",
                                      "comment": "Fermentación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FERMENTATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de fermentación no fue encontrada."
            )
    })
    @GetMapping
    public ResponseEntity<FermentationStageResource> getFermentationStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetFermentationStageByBatchIdQuery(batchId);
        var fermentationStage = queryService.getFermentationStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de fermentación para el lote de vino especificado."));

        var fermentationStageResource = FermentationStageResourceAssembler.toResource(fermentationStage);
        return ResponseEntity.ok(fermentationStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/fermentation-stage */
    @Operation(
            summary = "Create a new Fermentation Stage",
            description = "Crea una nueva etapa de fermentación para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de fermentación.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la fermentación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la fermentación.\n" +
                    "- **Yeast Used** (Double): es la cantidad de levadura utilizada en mg/L.\n" +
                    "- **Fermentation Type** (String): es el tipo de fermentación utilizada.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la fermentación.\n" +
                    "- **Final pH** (Double): es el pH final de la fermentación.\n" +
                    "- **Max Temperature** (Double): es la temperatura máxima durante la fermentación.\n" +
                    "- **Min Temperature** (Double): es la temperatura mínima durante la fermentación.\n" +
                    "- **Tank Code** (String): es el código del tanque donde se realiza la fermentación.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la fermentación.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de fermentación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de fermentación fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FermentationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de fermentación creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "fermentationStageId": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "yeastUsed": 150.0,
                                      "fermentationType": "Alcoholic",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "maxTemperature": 30.0,
                                      "minTemperature": 15.0,
                                      "tankCode": "TANK-01",
                                      "comment": "Fermentación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FERMENTATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de fermentación no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping
    public ResponseEntity<FermentationStageResource> addFermentationStageByBatch(@RequestBody @Valid CreateFermentationStageResource resource, @PathVariable Long batchId) {
        var command = CreateFermentationStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var fermentationStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de fermentación."));

        var fermentationStageResource = FermentationStageResourceAssembler.toResource(fermentationStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(fermentationStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/fermentation-stage */
    @Operation(
            summary = "Update Fermentation Stage",
            description = "Actualiza parcialmente una etapa de fermentación existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **fermentationStageId** (Long): es el ID de la etapa de fermentación.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la fermentación.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la fermentación.\n" +
                    "- **Yeast Used** (Double): es la cantidad de levadura utilizada en mg/L.\n" +
                    "- **Fermentation Type** (String): es el tipo de fermentación utilizada.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la fermentación.\n" +
                    "- **Final pH** (Double): es el pH final de la fermentación.\n" +
                    "- **Max Temperature** (Double): es la temperatura máxima durante la fermentación.\n" +
                    "- **Min Temperature** (Double): es la temperatura mínima durante la fermentación.\n" +
                    "- **Tank Code** (String): es el código del tanque donde se realiza la fermentación.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la fermentación.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de fermentación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de fermentación fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FermentationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de fermentación actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "fermentationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "yeastUsed": 150.0,
                                      "fermentationType": "Alcoholic",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "maxTemperature": 30.0,
                                      "minTemperature": 15.0,
                                      "tankCode": "TANK-01",
                                      "comment": "Fermentación realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FERMENTATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de fermentación no fue encontrada."
            )
    })
    @PatchMapping
    public ResponseEntity<FermentationStageResource> updateFermentationStage(@PathVariable Long batchId, @RequestBody @Valid UpdateFermentationStageResource resource) {
        var command = UpdateFermentationStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedFermentationStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de fermentación para actualizar."));

        var fermentationStageResource = FermentationStageResourceAssembler.toResource(updatedFermentationStage);
        return ResponseEntity.ok(fermentationStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/fermentation-stage */
    @Operation(
            summary = "Delete Fermentation Stage",
            description = "Elimina una etapa de fermentación por su ID. Esta operación eliminará permanentemente la etapa de fermentación del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de fermentación", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de fermentación fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de fermentación no fue encontrada."
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFermentationStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteFermentationStageByBatchCommand(batchId));
    }
}

