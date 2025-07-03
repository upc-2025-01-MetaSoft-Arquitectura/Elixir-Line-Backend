package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.DeleteAgingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.stages.GetAgingStageByBatchIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage.AgingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage.AgingStageQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.AgingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.CreateAgingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.CreateEmptyAgingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.UpdateAgingStageResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage.AgingStageResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage.CreateAgingStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage.CreateEmptyAgingStageCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage.UpdateAgingStageCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/batches/{batchId}", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Aging Stage", description = "Aging Stage Management Endpoints")
public class AgingStageController {
    private final AgingStageCommandService commandService;
    private final AgingStageQueryService queryService;

    public AgingStageController(AgingStageCommandService commandService, AgingStageQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }



    /* GET: /api/v1/batches/{batchId}/aging-stage */
    @Operation(
            summary = "Get Aging Stage by Wine Batch ID",
            description = "Recupera la etapa de añejamiento asociada a un lote de vino específico. Este endpoint devuelve los detalles de la etapa de añejamiento.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **agingStageId** (Long): es el ID de la etapa de añejamiento.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del añejamiento.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del añejamiento.\n" +
                    "- **Container Type** (String): es el tipo de contenedor utilizado para el añejamiento.\n" +
                    "- **Material** (String): es el material del contenedor.\n" +
                    "- **Container Code** (String): es el código del contenedor.\n" +
                    "- **Average Temperature** (Double): es la temperatura promedio durante el añejamiento.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Duration** (Integer): es la duración del añejamiento en meses.\n" +
                    "- **Frequency** (Integer): es la frecuencia de batonnage en días.\n" +
                    "- **Batonnage** (Double): es el número de veces que se realiza el batonnage.\n" +
                    "- **Refills** (Integer): es la cantidad de recargas realizadas.\n" +
                    "- **Racking** (Integer): es la cantidad de trasiegos realizados.\n" +
                    "- **Purpose** (String): es el propósito del añejamiento.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el añejamiento.\n" +
                    "- **Completion Status** (ENUM): indica si la fase ha sido completada o no, tiene 2 valores (COMPLETED y NOT_COMPLETED).\n" +
                    "- **Current Stage** (ENUM): es la etapa actual en la que nos encontramos, en este caso es AGING.\n" +
                    "- **Completed At** (LocalDateTime): es la fecha y hora en la que se ha completado la etapa, es decir, cuando el estado es COMPLETED.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para recuperar la etapa de añejamiento", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de añejamiento fue recuperada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de añejamiento",
                                    summary = "Respuesta exitosa con los detalles de la etapa de añejamiento",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "containerType": "Oak Barrel",
                                      "material": "Wood",
                                      "containerCode": "BARREL-01",
                                      "averageTemperature": 18.0,
                                      "volume": 1000.0,
                                      "duration": 12,
                                      "frequency": 30,
                                      "batonnage": 2.0,
                                      "refills": 1,
                                      "rackings": 2,
                                      "purpose": "Enhance flavor",
                                      "comment": "Añejamiento realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "AGING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El lote de vino o la etapa de añejamiento no fue encontrada."
            )
    })
    @GetMapping("/aging-stage")
    public ResponseEntity<AgingStageResource> getAgingStageByWineBatchId(@PathVariable Long batchId) {
        var query = new GetAgingStageByBatchIdQuery(batchId);
        var agingStage = queryService.getAgingStageByBatchId(query)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de añejamiento para el lote de vino especificado."));

        var agingStageResource = AgingStageResourceAssembler.toResource(agingStage);
        return ResponseEntity.ok(agingStageResource);
    }




    /* POST: /api/v1/batches/{batchId}/aging-stage */
    @Operation(
            summary = "Create a new Aging Stage",
            description = "Crea una nueva etapa de añejamiento para un lote de vino. Este endpoint requiere todos los campos obligatorios para registrar correctamente la etapa de añejamiento.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del añejamiento.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del añejamiento.\n" +
                    "- **Container Type** (String): es el tipo de contenedor utilizado para el añejamiento.\n" +
                    "- **Material** (String): es el material del contenedor.\n" +
                    "- **Container Code** (String): es el código del contenedor.\n" +
                    "- **Average Temperature** (Double): es la temperatura promedio durante el añejamiento.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Duration** (Integer): es la duración del añejamiento en meses.\n" +
                    "- **Frequency** (Integer): es la frecuencia de batonnage en días.\n" +
                    "- **Batonnage** (Double): es el número de veces que se realiza el batonnage.\n" +
                    "- **Refills** (Integer): es la cantidad de recargas realizadas.\n" +
                    "- **Racking** (Integer): es la cantidad de trasiegos realizados.\n" +
                    "- **Purpose** (String): es el propósito del añejamiento.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el añejamiento.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de añejamiento", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de añejamiento fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de añejamiento creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 2,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "containerType": "Oak Barrel",
                                      "material": "Wood",
                                      "containerCode": "BARREL-01",
                                      "averageTemperature": 18.0,
                                      "volume": 1000.0,
                                      "duration": 12,
                                      "frequency": 30,
                                      "batonnage": 2.0,
                                      "refills": 1,
                                      "rackings": 2,
                                      "purpose": "Enhance flavor",
                                      "comment": "Añejamiento realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "AGING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La etapa de añejamiento no fue creada. Datos inválidos o faltantes."
            )
    })
    @PostMapping("/aging-stage")
    public ResponseEntity<AgingStageResource> addAgingStageByBatch(@RequestBody @Valid CreateAgingStageResource resource, @PathVariable Long batchId) {
        var command = CreateAgingStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var agingStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de añejamiento."));

        var agingStageResource = AgingStageResourceAssembler.toResource(agingStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(agingStageResource);
    }



    /* POST: /api/v1/batches/{batchId}/aging-stage */
    @Operation(
            summary = "Create an empty Aging Stage for a Wine Batch ID",
            description = "Crea una nueva etapa de añejamiento con todos los campos vacíos. Este endpoint no requiere ninguno de los campos para registrar correctamente la etapa de añejamiento.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del añejamiento.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del añejamiento.\n" +
                    "- **Container Type** (String): es el tipo de contenedor utilizado para el añejamiento.\n" +
                    "- **Material** (String): es el material del contenedor.\n" +
                    "- **Container Code** (String): es el código del contenedor.\n" +
                    "- **Average Temperature** (Double): es la temperatura promedio durante el añejamiento.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Duration** (Integer): es la duración del añejamiento en meses.\n" +
                    "- **Frequency** (Integer): es la frecuencia de batonnage en días.\n" +
                    "- **Batonnage** (Double): es el número de veces que se realiza el batonnage.\n" +
                    "- **Refills** (Integer): es la cantidad de recargas realizadas.\n" +
                    "- **Racking** (Integer): es la cantidad de trasiegos realizados.\n" +
                    "- **Purpose** (String): es el propósito del añejamiento.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el añejamiento.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se crea la etapa de añejamiento", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "La etapa de añejamiento fue creada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de añejamiento creada",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 2,
                                      "batchId": 10,
                                      "employee": null,
                                      "startDate": null,
                                      "endDate": null,
                                      "containerType": null,
                                      "material": null,
                                      "containerCode": null,
                                      "averageTemperature": null,
                                      "volume": null,
                                      "duration": null,
                                      "frequency": null,
                                      "batonnage": null,
                                      "refills": null,
                                      "rackings": null,
                                      "purpose": null,
                                      "comment": null,
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "AGING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("empty/aging-stage")
    public ResponseEntity<AgingStageResource> addEmptyAgingStageByBatch(@RequestBody @Valid CreateEmptyAgingStageResource resource, @PathVariable Long batchId) {
        var command = CreateEmptyAgingStageCommandFromResourceAssembler.toCommandFromResource(resource, batchId);
        var agingStage = commandService.handle(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la etapa de añejamiento."));

        var agingStageResource = AgingStageResourceAssembler.toResource(agingStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(agingStageResource);
    }




    /* PATCH: /api/v1/batches/{batchId}/aging-stage */
    @Operation(
            summary = "Update Aging Stage",
            description = "Actualiza parcialmente una etapa de añejamiento existente. Solo se actualizarán los campos especificados.\n\n" +
                    "### Significado de los Atributos:\n" +
                    "- **agingStageId** (Long): es el ID de la etapa de añejamiento.\n" +
                    "- **batchId** (Long): es el ID que representa que esta fase está relacionada con un lote específico.\n" +
                    "- **Employee** (String): es el encargado del registro de los datos.\n" +
                    "- **Start Date** (LocalDate): es la fecha de inicio del añejamiento.\n" +
                    "- **End Date** (LocalDate): es la fecha de finalización del añejamiento.\n" +
                    "- **Container Type** (String): es el tipo de contenedor utilizado para el añejamiento.\n" +
                    "- **Material** (String): es el material del contenedor.\n" +
                    "- **Container Code** (String): es el código del contenedor.\n" +
                    "- **Average Temperature** (Double): es la temperatura promedio durante el añejamiento.\n" +
                    "- **Volume** (Double): es el volumen de vino en litros.\n" +
                    "- **Duration** (Integer): es la duración del añejamiento en meses.\n" +
                    "- **Frequency** (Integer): es la frecuencia de batonnage en días.\n" +
                    "- **Batonnage** (Double): es el número de veces que se realiza el batonnage.\n" +
                    "- **Refills** (Integer): es la cantidad de recargas realizadas.\n" +
                    "- **Racking** (Integer): es la cantidad de trasiegos realizados.\n" +
                    "- **Purpose** (String): es el propósito del añejamiento.\n" +
                    "- **Comment** (String): son los comentarios adicionales sobre el añejamiento.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se actualiza la etapa de añejamiento", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La etapa de añejamiento fue actualizada exitosamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgingStageResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de etapa de añejamiento actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "id": 1,
                                      "batchId": 10,
                                      "employee": "Luis Carlos Prada Naez",
                                      "startDate": "2025-06-01",
                                      "endDate": "2025-06-02",
                                      "containerType": "Oak Barrel",
                                      "material": "Wood",
                                      "containerCode": "BARREL-01",
                                      "averageTemperature": 18.0,
                                      "volume": 1000.0,
                                      "duration": 12,
                                      "frequency": 30,
                                      "batonnage": 2.0,
                                      "refills": 1,
                                      "rackings": 2,
                                      "purpose": "Enhance flavor",
                                      "comment": "Añejamiento realizado con éxito.",
                                      "completionStatus": "NOT_COMPLETED",
                                      "currentStage": "AGING",
                                      "completedAt": null,
                                      "dataHash": null
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de añejamiento no fue encontrada."
            )
    })
    @PatchMapping("/aging-stage")
    public ResponseEntity<AgingStageResource> updateAgingStage(@PathVariable Long batchId, @RequestBody @Valid UpdateAgingStageResource resource) {
        var command = UpdateAgingStageCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedAgingStage = commandService.update(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la etapa de añejamiento para actualizar."));

        var agingStageResource = AgingStageResourceAssembler.toResource(updatedAgingStage);
        return ResponseEntity.ok(agingStageResource);
    }




    /* DELETE: /api/v1/batches/{batchId}/aging-stage */
    @Operation(
            summary = "Delete Aging Stage",
            description = "Elimina una etapa de añejamiento por su ID. Esta operación eliminará permanentemente la etapa de añejamiento del sistema.",
            parameters = {
                    @Parameter(name = "batchId", description = "ID del lote de vino para el cual se elimina la etapa de añejamiento", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La etapa de añejamiento fue eliminada exitosamente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La etapa de añejamiento no fue encontrada."
            )
    })
    @DeleteMapping("/aging-stage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgingStage(@PathVariable Long batchId) {
        commandService.delete(new DeleteAgingStageByBatchCommand(batchId));
    }
}

