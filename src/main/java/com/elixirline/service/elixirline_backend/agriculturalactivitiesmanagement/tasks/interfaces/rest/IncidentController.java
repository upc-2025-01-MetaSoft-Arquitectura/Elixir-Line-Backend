package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetIncidentByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.IncidentCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.IncidentQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentWithTaskInfoResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.CreateIncidentCommandFromRequestAssembler;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.IncidentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
@Tag(name = "Incident", description = "Incident Management Endpoints")
public class IncidentController {
    private final IncidentCommandService incidentCommandService;
    private final IncidentQueryService incidentQueryService;

    public IncidentController(IncidentCommandService incidentCommandService, IncidentQueryService incidentQueryService) {
        this.incidentCommandService = incidentCommandService;
        this.incidentQueryService = incidentQueryService;
    }

    /* POST: /api/v1/incidents */
    @Operation(
            summary = "Crear incidencia para una tarea",
            description = "Crea una nueva incidencia con imagen y descripción para una tarea específica.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            examples = @ExampleObject(
                                    name = "Ejemplo de incidencia",
                                    summary = "Formulario de ejemplo",
                                    value = """
                                            taskId: 1
                                            description: Hubo un problema con el equipo de riego.
                                            imageFile: (seleccionar archivo .jpg o .png)
                                            """
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Incidencia creada correctamente",
            content = @Content(schema = @Schema(implementation = IncidentResource.class))
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IncidentResource> createIncident(
            @Parameter(description = "ID de la tarea relacionada", required = true)
            @RequestParam("taskId") Long taskId,

            @Parameter(description = "Descripción de la incidencia", required = true)
            @RequestParam("description") String description,

            @Parameter(description = "Archivo de imagen (.jpg o .png)", required = true)
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        var command = CreateIncidentCommandFromRequestAssembler.toCommand(taskId, description, imageFile);
        var incident = incidentCommandService.handle(command).orElseThrow();
        var resource = IncidentResourceFromEntityAssembler.toResourceFromEntity(incident);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

//    /* GET: /api/v1/incidents/task/{taskId} */
//    @Operation(
//            summary = "Obtener incidencias por ID de tarea",
//            description = "Devuelve una lista de incidencias asociadas a una tarea específica."
//    )
//    @GetMapping("/task/{taskId}")
//    public ResponseEntity<List<IncidentResource>> getIncidentsByTaskId(@PathVariable Long taskId) {
//        var query = new GetIncidentByTaskIdQuery(taskId);
//        var incidents = incidentQueryService.handle(query);
//        if (incidents.isEmpty()) return ResponseEntity.notFound().build();
//        var resources = incidents.stream().map(IncidentResourceFromEntityAssembler::toResourceFromEntity).toList();
//        return ResponseEntity.ok(resources);
//    }

    /* PATCH: /api/v1/incidents/{incidentId} */
    @Operation(summary = "Actualizar parcialmente una incidencia")
    @PatchMapping(value ="/{incidentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IncidentResource> patchIncident(
            @PathVariable Long incidentId,
            @RequestParam(required = false) Long taskId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile imageFile
    ) {
        var command = new PatchIncidentCommand(incidentId, taskId, description, imageFile);
        var updated = incidentCommandService.handle(command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();

        var resource = IncidentResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(resource);
    }

    /* DELETE: /api/v1/incidents/{incidentId} */
    @Operation(summary = "Eliminar una incidencia por ID")
    @DeleteMapping("/{incidentId}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long incidentId) {
        try {
            incidentCommandService.handle(new DeleteIncidentCommand(incidentId));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // GET: industrial
    @Operation(
            summary = "Listar incidencias de tipo industrial",
            description = "Devuelve una lista de incidencias asociadas a tareas de tipo industrial. Incluye información relevante de la tarea relacionada como título, lote, encargado y tipo de tarea.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de incidencias encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = IncidentWithTaskInfoResource.class)
                            )
                    )
            }
    )
    @GetMapping("/with-task-info/industrial")
    public ResponseEntity<List<IncidentWithTaskInfoResource>> getIndustrialIncidents() {
        var list = incidentQueryService.handleByTaskType(TaskType.TASK_INDUSTRY);
        return ResponseEntity.ok(list);
    }

    // GET: field
    @Operation(
            summary = "Listar incidencias de tipo campo",
            description = "Devuelve una lista de incidencias asociadas a tareas de tipo campo. Incluye información relevante de la tarea relacionada como título, lote, encargado y tipo de tarea.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de incidencias encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = IncidentWithTaskInfoResource.class)
                            )
                    )
            }
    )
    @GetMapping("/with-task-info/field")
    public ResponseEntity<List<IncidentWithTaskInfoResource>> getFieldIncidents() {
        var list = incidentQueryService.handleByTaskType(TaskType.TASK_FIELD);
        return ResponseEntity.ok(list);
    }

    // GET: incidentId
    @Operation(
            summary = "Obtener una incidencia por su ID",
            description = "Devuelve los detalles de una incidencia específica a partir de su ID.",
            parameters = {
                    @Parameter(name = "incidentId", description = "ID de la incidencia", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Incidencia encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = IncidentResource.class),
                                    examples = @ExampleObject(
                                            name = "Incidencia encontrada",
                                            summary = "Ejemplo de una incidencia",
                                            value = """
                    {
                      "id": 3,
                      "taskId": 12,
                      "description": "Problema en el filtro de agua",
                      "imageUrl": "https://elixirupc.blob.core.windows.net/incidents/filtro.jpg",
                      "createdAt": "2025-07-03T11:20:00Z",
                      "updatedAt": "2025-07-03T11:20:00Z"
                    }
                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la incidencia",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(
                                            value = """
                    {
                      "status": "ERROR",
                      "message": "Incidencia no encontrada.",
                      "details": [
                        "No existe una incidencia con ese ID."
                      ]
                    }
                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{incidentId}")
    public ResponseEntity<IncidentResource> getIncidentById(@PathVariable Long incidentId) {
        var optional = incidentQueryService.handle(incidentId);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        var resource = IncidentResourceFromEntityAssembler.toResourceFromEntity(optional.get());
        return ResponseEntity.ok(resource);
    }

}
