package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.DeleteCorrectionStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetCorrectionStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage.CorrectionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage.CorrectionStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.CorrectionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.CreateCorrectionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.UpdateCorrectionStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage.CorrectionStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage.CreateCorrectionStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage.UpdateCorrectionStageCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/batches/{batchId}/correction-stage", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Correction Stage", description = "Correction Stage Management Endpoints")
public class CorrectionStageController {
    private final CorrectionStageCommandService commandService;
    private final CorrectionStageQueryService queryService;

    public CorrectionStageController(CorrectionStageCommandService commandService, CorrectionStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /* GET: /api/v1/batches/{batchId}/correction-stage */
    @Operation(
            summary = "Get Correction Stage by Wine Batch ID",
            description = "Recupera la etapa de corrección asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de corrección.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **correctionStageId** (Long): es el ID de la etapa de corrección.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la corrección.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la corrección.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Added Sugar** (Double): es la cantidad de azúcar añadida en kg.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la corrección.\n" +
                    "- **Final pH** (Double): es el pH final de la corrección.\n" +
                    "- **Acid Type** (String): es el tipo de ácido utilizado en la corrección.\n" +
                    "- **Added Acid** (Double): es la cantidad de ácido añadido en g/L.\n" +
                    "- **Added Sulphites** (Double): es la cantidad de sulfitos añadidos en mg/L.\n" +
                    "- **Nutrients** (List<String>): son los nutrientes añadidos durante la corrección.\n" +
                    "- **Justification** (String): es la justificación para la corrección realizada.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la corrección.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es CORRECTION.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de corrección", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de corrección fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CorrectionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de corrección",
                                    summary = "Respuesta exitosa con los detalles de la etapa de corrección",
                                    value = """
                                    {
                                      "correctionStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "addedSugar": 2.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "acidType": "Tartaric",
                                      "addedAcid": 1.5,
                                      "addedSulphites": 50.0,
                                      "nutrients": ["Nutrient A", "Nutrient B"],
                                      "justification": "Adjustment for fermentation.",
                                      "comment": "Corrección realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CORRECTION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de corrección no fue encontrada."
            )
    })
    @GetMapping
    public ResponseEntity<CorrectionStageResource> getCorrectionStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetCorrectionStageByBatchIdQuery(batchId);
        var correctionStage = queryService.getCorrectionStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de corrección para el lote de vino especificado."));

        var correctionStageResource = CorrectionStageResourceAssembler.toResource(correctionStage);
        return ResponseEntity.ok(correctionStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/correction-stage */
    @Operation(
            summary = "Create a new Correction Stage",
            description = "Crea una nueva etapa de corrección para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de corrección.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la corrección.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la corrección.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Added Sugar** (Double): es la cantidad de azúcar añadida en kg.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la corrección.\n" +
                    "- **Final pH** (Double): es el pH final de la corrección.\n" +
                    "- **Acid Type** (String): es el tipo de ácido utilizado en la corrección.\n" +
                    "- **Added Acid** (Double): es la cantidad de ácido añadido en g/L.\n" +
                    "- **Added Sulphites** (Double): es la cantidad de sulfitos añadidos en mg/L.\n" +
                    "- **Nutrients** (List<String>): son los nutrientes añadidos durante la corrección.\n" +
                    "- **Justification** (String): es la justificación para la corrección realizada.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la corrección.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de corrección", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de corrección fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CorrectionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de corrección creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "correctionStageId": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "addedSugar": 2.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "acidType": "Tartaric",
                                      "addedAcid": 1.5,
                                      "addedSulphites": 50.0,
                                      "nutrients": ["Nutrient A", "Nutrient B"],
                                      "justification": "Adjustment for fermentation.",
                                      "comment": "Corrección realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CORRECTION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de corrección no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping
    public ResponseEntity<CorrectionStageResource> addCorrectionStageByBatch(@RequestBody @Valid CreateCorrectionStageResource resource, @PathVariable Long batchId) {
        var command = CreateCorrectionStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var correctionStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de corrección."));

        var correctionStageResource = CorrectionStageResourceAssembler.toResource(correctionStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(correctionStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/correction-stage */
    @Operation(
            summary = "Update Correction Stage",
            description = "Actualiza parcialmente una etapa de corrección existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **correctionStageId** (Long): es el ID de la etapa de corrección.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio de la corrección.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización de la corrección.\n" +
                    "- **Initial Sugar Level** (Double): es el nivel de azúcar inicial en °Brix.\n" +
                    "- **Final Sugar Level** (Double): es el nivel de azúcar final en °Brix.\n" +
                    "- **Added Sugar** (Double): es la cantidad de azúcar añadida en kg.\n" +
                    "- **Initial pH** (Double): es el pH inicial de la corrección.\n" +
                    "- **Final pH** (Double): es el pH final de la corrección.\n" +
                    "- **Acid Type** (String): es el tipo de ácido utilizado en la corrección.\n" +
                    "- **Added Acid** (Double): es la cantidad de ácido añadido en g/L.\n" +
                    "- **Added Sulphites** (Double): es la cantidad de sulfitos añadidos en mg/L.\n" +
                    "- **Nutrients** (List<String>): son los nutrientes añadidos durante la corrección.\n" +
                    "- **Justification** (String): es la justificación para la corrección realizada.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre la corrección.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de corrección", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de corrección fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CorrectionStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de corrección actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "correctionStageId": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "initialSugarLevel": 22.0,
                                      "finalSugarLevel": 5.0,
                                      "addedSugar": 2.0,
                                      "initialPH": 3.5,
                                      "finalPH": 3.2,
                                      "acidType": "Tartaric",
                                      "addedAcid": 1.5,
                                      "addedSulphites": 50.0,
                                      "nutrients": ["Nutrient A", "Nutrient B"],
                                      "justification": "Adjustment for fermentation.",
                                      "comment": "Corrección realizada con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "CORRECTION",
                                      "completedAt": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de corrección no fue encontrada."
            )
    })
    @PatchMapping
    public ResponseEntity<CorrectionStageResource> updateCorrectionStage(@PathVariable Long batchId, @RequestBody @Valid UpdateCorrectionStageResource resource) {
        var command = UpdateCorrectionStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedCorrectionStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de corrección para actualizar."));

        var correctionStageResource = CorrectionStageResourceAssembler.toResource(updatedCorrectionStage);
        return ResponseEntity.ok(correctionStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/correction-stage */
    @Operation(
            summary = "Delete Correction Stage",
            description = "Elimina una etapa de corrección por su ID. Esta operación eliminará permanentemente la etapa de corrección del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de corrección", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de corrección fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de corrección no fue encontrada."
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCorrectionStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteCorrectionStageByBatchCommand(batchId));
    }
}

