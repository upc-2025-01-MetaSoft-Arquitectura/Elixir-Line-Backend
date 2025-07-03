package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.EvidenceResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.CreateEvidenceCommandFromRequestAssembler;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.EvidenceResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidences")
@Tag(name = "Evidence", description = "Evidence Management Endpoints")
public class EvidenceController {
    private final EvidenceCommandService evidenceCommandService;
    private final EvidenceQueryService evidenceQueryService;
    public EvidenceController(EvidenceCommandService evidenceCommandService, EvidenceQueryService evidenceQueryService) {
        this.evidenceCommandService = evidenceCommandService;
        this.evidenceQueryService = evidenceQueryService;
    }

    /* POST: /api/v1/evidences */
    @Operation(
            summary = "Crear evidencia para una tarea",
            description = "Crea una nueva evidencia con imagen, descripción y porcentaje de avance para una tarea específica.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            examples = @ExampleObject(
                                    name = "Ejemplo de evidencia",
                                    summary = "Formulario de ejemplo",
                                    value = """
                                            taskId: 1
                                            description: Se avanzó el 30% en la instalación.
                                            progressPercentage: 30
                                            imageFile: (seleccionar archivo .jpg o .png)
                                            """
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Evidencia creada correctamente",
            content = @Content(schema = @Schema(implementation = EvidenceResource.class))
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EvidenceResource> createEvidence(
            @Parameter(description = "ID de la tarea a la que pertenece la evidencia", required = true)
            @RequestParam("taskId") Long taskId,

            @Parameter(description = "Descripción del avance", required = true)
            @RequestParam("description") String description,

            @Parameter(description = "Porcentaje de avance (Ej: 10, 20, 30...)", required = true)
            @RequestParam("progressPercentage") Integer progressPercentage,

            @Parameter(description = "Archivo de imagen de la evidencia (.jpg o .png)", required = true)
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        var command = CreateEvidenceCommandFromRequestAssembler.toCommand(taskId, description, progressPercentage, imageFile);
        var evidence = evidenceCommandService.handle(command).orElseThrow();
        var resource = EvidenceResourceFromEntityAssembler.toResourceFromEntity(evidence);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    /* GET: /api/v1/evidences/task/{taskId} */
    @Operation(
            summary = "Obtener evidencias por ID de tarea",
            description = "Devuelve una lista de evidencias asociadas a una tarea específica.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID de la tarea", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Evidencias encontradas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EvidenceResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de evidencias",
                                            summary = "Lista de evidencias para una tarea",
                                            value = """
                            [
                              {
                                "id": 1,
                                "taskId": 10,
                                "progressPercentage": 30,
                                "description": "Inicio del trabajo",
                                "imageUrl": "https://mystorage.blob.core.windows.net/images/evidence1.jpg",
                                "createdAt": "2025-06-28T10:45:00Z",
                                "updatedAt": "2025-06-28T10:45:00Z"
                              },
                              {
                                "id": 2,
                                "taskId": 10,
                                "progressPercentage": 70,
                                "description": "Avance significativo",
                                "imageUrl": "https://mystorage.blob.core.windows.net/images/evidence2.jpg",
                                "createdAt": "2025-06-29T14:30:00Z",
                                "updatedAt": "2025-06-29T14:30:00Z"
                              }
                            ]
                        """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron evidencias para esta tarea",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de no encontrado",
                                            summary = "No hay evidencias para esta tarea",
                                            value = """
                            {
                              "status": "ERROR",
                              "message": "No se encontraron evidencias.",
                              "details": [
                                "No hay evidencias asociadas a esta tarea."
                              ]
                            }
                        """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<EvidenceResource>> getEvidencesByTaskId(@PathVariable Long taskId) {
        var query = new GetEvidenceByTaskIdQuery(taskId);
        var evidences = evidenceQueryService.handle(query);
        if (evidences.isEmpty()) return ResponseEntity.notFound().build();
        var resources = evidences.stream().map(EvidenceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }


    /* PATCH: /api/v1/evidences/task/{taskId} */
    @Operation(
            summary = "Actualizar parcialmente una evidencia",
            description = "Permite actualizar uno o varios campos de una evidencia existente, incluyendo la imagen, descripción, porcentaje de avance o taskId.",
            parameters = {
                    @Parameter(name = "evidenceId", description = "ID de la evidencia a actualizar", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            examples = @ExampleObject(
                                    name = "Ejemplo PATCH",
                                    summary = "Ejemplo de actualización parcial",
                                    value = """
                    taskId: 2
                    description: Nueva descripción
                    progressPercentage: 60
                    imageFile: (seleccionar nueva imagen)
                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evidencia actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Evidencia no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error al actualizar evidencia")
    })
    @PatchMapping(value = "/{evidenceId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EvidenceResource> patchEvidence(
            @PathVariable Long evidenceId,
            @RequestParam(required = false) Long taskId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer progressPercentage,
            @RequestParam(required = false) MultipartFile imageFile
    ) {
        var command = new PatchEvidenceCommand(evidenceId, taskId, description, progressPercentage, imageFile);
        var updated = evidenceCommandService.handle(command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();

        var resource = EvidenceResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(resource);
    }


    @Operation(
            summary = "Eliminar evidencia",
            description = "Elimina una evidencia existente por su ID.",
            parameters = {
                    @Parameter(name = "evidenceId", description = "ID de la evidencia a eliminar", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evidencia eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Evidencia no encontrada",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Evidencia no encontrada",
                                            summary = "ID no existe",
                                            value = """
                        {
                          "status": "ERROR",
                          "message": "Evidencia no encontrada.",
                          "details": ["No existe una evidencia con ese ID."]
                        }
                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{evidenceId}")
    public ResponseEntity<Void> deleteEvidence(@PathVariable Long evidenceId) {
        try {
            evidenceCommandService.handle(new DeleteEvidenceCommand(evidenceId));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
