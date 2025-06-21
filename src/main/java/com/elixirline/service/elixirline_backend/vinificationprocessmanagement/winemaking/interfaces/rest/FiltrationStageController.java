package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.DeleteFiltrationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetFiltrationStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage.FiltrationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage.FiltrationStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.CreateFiltrationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.FiltrationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.UpdateFiltrationStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage.CreateFiltrationStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage.FiltrationStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage.UpdateFiltrationStageCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/batches/{batchId}/filtration-stage", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Filtration Stage", description = "Filtration Stage Management Endpoints")
public class FiltrationStageController {
    private final FiltrationStageCommandService commandService;
    private final FiltrationStageQueryService queryService;

    public FiltrationStageController(FiltrationStageCommandService commandService, FiltrationStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /* GET: /api/v1/batches/{batchId}/filtration-stage */
    @Operation(
            summary = "Get Filtration Stage by Wine Batch ID",
            description = "Recupera la etapa de filtración asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de filtración.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **filtrationStageId** (Long): es el ID de la etapa de filtración.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la filtración.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la filtración.\n" +
                    "- **Filter Type** (String): es el tipo de filtración utilizada.\n" +
                    "- **Filter Medium** (String): es el medio filtrante utilizado.\n" +
                    "- **Porosity** (Double): es la porosidad del medio filtrante en micrones.\n" +
                    "- **Initial Turbidity** (Double): es el nivel de turbidez antes de la filtración.\n" +
                    "- **Final Turbidity** (Double): es el nivel de turbidez después de la filtración.\n" +
                    "- **Temperature** (Double): es la temperatura durante la filtración.\n" +
                    "- **Pressure** (Double): es la presión de la filtración en bares.\n" +
                    "- **Filtered Volume** (Double): es el volumen filtrado en litros.\n" +
                    "- **Sterile Filtration** (Boolean): indica si se realizó una filtración estéril.\n" +
                    "- **Changed Filtration** (Boolean): indica si se cambió la filtración, habilitando el motivo de cambio.\n" +
                    "- **Change Reason** (String): es el motivo del cambio en la filtración, si aplica.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la filtración.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es FILTRATION.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de filtración", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de filtración fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FiltrationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de filtración",
                                    summary = "Respuesta exitosa con los detalles de la etapa de filtración",
                                    value = """
                                    {
                                      "filtrationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "filterType": "Microfiltration",
                                      "filterMedium": "Polypropylene",
                                      "porosity": 0.2,
                                      "initialTurbidity": 5.0,
                                      "finalTurbidity": 0.5,
                                      "temperature": 20.0,
                                      "pressure": 1.5,
                                      "filteredVolume": 1000.0,
                                      "sterileFiltration": true,
                                      "changedFiltration": false,
                                      "changeReason": null,
                                      "comment": "Filtración realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FILTRATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de filtración no fue encontrada."
            )
    })
    @GetMapping
    public ResponseEntity<FiltrationStageResource> getFiltrationStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetFiltrationStageByBatchIdQuery(batchId);
        var filtrationStage = queryService.getFiltrationStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de filtración para el lote de vino especificado."));

        var filtrationStageResource = FiltrationStageResourceAssembler.toResource(filtrationStage);
        return ResponseEntity.ok(filtrationStageResource);
    }

    /* POST: /api/v1/batches/{batchId}/filtration-stage */
    @Operation(
            summary = "Create a new Filtration Stage",
            description = "Crea una nueva etapa de filtración para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de filtración.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la filtración.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la filtración.\n" +
                    "- **Filter Type** (String): es el tipo de filtración utilizada.\n" +
                    "- **Filter Medium** (String): es el medio filtrante utilizado.\n" +
                    "- **Porosity** (Double): es la porosidad del medio filtrante en micrones.\n" +
                    "- **Initial Turbidity** (Double): es el nivel de turbidez antes de la filtración.\n" +
                    "- **Final Turbidity** (Double): es el nivel de turbidez después de la filtración.\n" +
                    "- **Temperature** (Double): es la temperatura durante la filtración.\n" +
                    "- **Pressure** (Double): es la presión de la filtración en bares.\n" +
                    "- **Filtered Volume** (Double): es el volumen filtrado en litros.\n" +
                    "- **Sterile Filtration** (Boolean): indica si se realizó una filtración estéril.\n" +
                    "- **Changed Filtration** (Boolean): indica si se cambió la filtración, habilitando el motivo de cambio.\n" +
                    "- **Change Reason** (String): es el motivo del cambio en la filtración, si aplica.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la filtración.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de filtración", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de filtración fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FiltrationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de filtración creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "filtrationStageId": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "filterType": "Microfiltration",
                                      "filterMedium": "Polypropylene",
                                      "porosity": 0.2,
                                      "initialTurbidity": 5.0,
                                      "finalTurbidity": 0.5,
                                      "temperature": 20.0,
                                      "pressure": 1.5,
                                      "filteredVolume": 1000.0,
                                      "sterileFiltration": true,
                                      "changedFiltration": false,
                                      "changeReason": null,
                                      "comment": "Filtración realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FILTRATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de filtración no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping
    public ResponseEntity<FiltrationStageResource> addFiltrationStageByBatch(@RequestBody @Valid CreateFiltrationStageResource resource, @PathVariable Long batchId) {
        var command = CreateFiltrationStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var filtrationStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de filtración."));

        var filtrationStageResource = FiltrationStageResourceAssembler.toResource(filtrationStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(filtrationStageResource);
    }

    /* PATCH: /api/v1/batches/{batchId}/filtration-stage */
    @Operation(
            summary = "Update Filtration Stage",
            description = "Actualiza parcialmente una etapa de filtración existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **filtrationStageId** (Long): es el ID de la etapa de filtración.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la filtración.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la filtración.\n" +
                    "- **Filter Type** (String): es el tipo de filtración utilizada.\n" +
                    "- **Filter Medium** (String): es el medio filtrante utilizado.\n" +
                    "- **Porosity** (Double): es la porosidad del medio filtrante en micrones.\n" +
                    "- **Initial Turbidity** (Double): es el nivel de turbidez antes de la filtración.\n" +
                    "- **Final Turbidity** (Double): es el nivel de turbidez después de la filtración.\n" +
                    "- **Temperature** (Double): es la temperatura durante la filtración.\n" +
                    "- **Pressure** (Double): es la presión de la filtración en bares.\n" +
                    "- **Filtered Volume** (Double): es el volumen filtrado en litros.\n" +
                    "- **Sterile Filtration** (Boolean): indica si se realizó una filtración estéril.\n" +
                    "- **Changed Filtration** (Boolean): indica si se cambió la filtración, habilitando el motivo de cambio.\n" +
                    "- **Change Reason** (String): es el motivo del cambio en la filtración, si aplica.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la filtración.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de filtración", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de filtración fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FiltrationStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de filtración actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "filtrationStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "filterType": "Microfiltration",
                                      "filterMedium": "Polypropylene",
                                      "porosity": 0.2,
                                      "initialTurbidity": 5.0,
                                      "finalTurbidity": 0.5,
                                      "temperature": 20.0,
                                      "pressure": 1.5,
                                      "filteredVolume": 1000.0,
                                      "sterileFiltration": true,
                                      "changedFiltration": false,
                                      "changeReason": null,
                                      "comment": "Filtración realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "FILTRATION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de filtración no fue encontrada."
            )
    })
    @PatchMapping
    public ResponseEntity<FiltrationStageResource> updateFiltrationStage(@PathVariable Long batchId, @RequestBody @Valid UpdateFiltrationStageResource resource) {
        var command = UpdateFiltrationStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedFiltrationStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de filtración para actualizar."));

        var filtrationStageResource = FiltrationStageResourceAssembler.toResource(updatedFiltrationStage);
        return ResponseEntity.ok(filtrationStageResource);
    }

    /* DELETE: /api/v1/batches/{batchId}/filtration-stage */
    @Operation(
            summary = "Delete Filtration Stage",
            description = "Elimina una etapa de filtración por su ID. Esta operación eliminará permanentemente la etapa de filtración del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de filtración", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de filtración fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de filtración no fue encontrada."
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFiltrationStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteFiltrationStageByBatchCommand(batchId));
    }
}

