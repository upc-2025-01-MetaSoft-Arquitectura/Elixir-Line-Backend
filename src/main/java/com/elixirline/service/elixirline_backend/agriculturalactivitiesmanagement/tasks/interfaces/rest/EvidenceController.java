package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.*;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value ="/api/v1/evidences", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Evidence", description = "Evidence Management Endpoints")
@RequiredArgsConstructor
public class EvidenceController {
    private final EvidenceCommandService evidenceCommandService;
    private final EvidenceQueryService evidenceQueryService;

    /* POST: /api/v1/evidences */
    @Operation(
            summary = "Create a new evidence",
            description = "Crear una nueva evidencia con imagen y descripción.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la evidencia a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateEvidenceResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación de evidencia",
                                    summary = "Solicitud de creación de evidencia",
                                    value = """
                                    {
                                      "taskId": 3,
                                      "description": "Fórmula mejorada para mayor rendimiento",
                                      "progressPercentage": 30,
                                      "image": "https://storage.azure.net/insumos/fertilizante-1.jpg"
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve la evidencia creada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EvidenceResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de evidencia creada",
                                    summary = "Respuesta exitosa con los detalles de la evidencia creada",
                                    value = """
                                    {
                                      "id": 1,
                                      "taskId": 10,
                                      "progressPercentage": 30,
                                      "description": "Inicio del trabajo",
                                      "imageUrl": "https://mystorage.blob.core.windows.net/images/evidence1.jpg",
                                      "createdAt": "2025-06-28T10:45:00Z",
                                      "updatedAt": "2025-06-28T10:45:00Z"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta. Los datos proporcionados no son válidos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado. Token inválido o no proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Token inválido",
                                    summary = "No se proporcionó token Bearer",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Token inválido o no proporcionado.",
                                      "details": [
                                        "Debe incluir el encabezado Authorization con el token Bearer."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar crear la evidencia.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al crear la evidencia",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo crear la evidencia.",
                                      "details": [
                                        "Error en el servicio de creación de evidencia.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EvidenceResource> createEvidence(
            @RequestPart(value = "evidence") @Valid CreateEvidenceResource resource,
            @RequestPart ( value = "image", required = false) MultipartFile image
    ) {
        try{
            var command = CreateEvidenceCommandFromRequestAssembler.toCommand(resource, image);
            var evidence = evidenceCommandService.handle(command)
                    .orElseThrow( () -> new RuntimeException( "Error al crear la evidencia" ) );

            var evidenceResource = EvidenceResourceFromEntityAssembler.toResourceFromEntity(evidence);
            return ResponseEntity.status(HttpStatus.CREATED).body(evidenceResource);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
            summary = "Update Reception Stage",
            description = "Actualiza parcialmente una evidencia existente. Solo se actualizarán los campos especificados",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la evidencia a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PatchEvidenceResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización de evidencia",
                                    summary = "Solicitud de actualización de evidencia",
                                    value = """
                                    {
                                      "taskId": 3,
                                      "description": "Fórmula mejorada para mayor rendimiento",
                                      "progressPercentage": 30,
                                      "image": "https://storage.azure.net/insumos/fertilizante-1.jpg"
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "La evidencia fue actualizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EvidenceResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de evidencia actualizada",
                                    summary = "Respuesta exitosa de actualización",
                                    value = """
                                    {
                                      "id": 1,
                                      "taskId": 10,
                                      "progressPercentage": 30,
                                      "description": "Inicio del trabajo",
                                      "imageUrl": "https://mystorage.blob.core.windows.net/images/evidence1.jpg",
                                      "createdAt": "2025-06-28T10:45:00Z",
                                      "updatedAt": "2025-06-28T10:45:00Z"
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
    @PatchMapping(value = "/{evidenceId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EvidenceResource> patchEvidence(
            @PathVariable Long evidenceId,
            @RequestPart(required = false) String taskId,
            @RequestPart(required = false) String description,
            @RequestPart(required = false) String progressPercentage,
            @RequestPart(required = false) MultipartFile imageFile
    ) {
        Long taskIdLong = (taskId != null && !taskId.isEmpty()) ? Long.parseLong(taskId) : null;
        Integer progressPercentageInt = (progressPercentage != null && !progressPercentage.isEmpty()) ? Integer.parseInt(progressPercentage) : null;

        var command = new PatchEvidenceCommand(evidenceId, taskIdLong, description, progressPercentageInt, imageFile);
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
