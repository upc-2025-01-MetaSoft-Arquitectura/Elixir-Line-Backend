package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.FieldWorkerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.ActivateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.DeleteFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetFieldWorkerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetFieldWorkerByUserIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.FieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.PartialUpdateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.CreateFieldWorkerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.FieldWorkerResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.PartialUpdateFieldWorkerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.UpdateFieldWorkerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/fieldworkers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Field Workers", description = "Field Worker Management Endpoints")
public class FieldWorkerController {
    private final FieldWorkerCommandService commandService;
    private final FieldWorkerQueryService queryService;

    public FieldWorkerController(FieldWorkerCommandService commandService, FieldWorkerQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /*GET: /api/v1/fieldworkers*/
    @Operation(
            summary = "Get all field workers",
            description = "Recupera una lista de todos los trabajadores de campo disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de trabajadores de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FieldWorkerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de trabajadores de campo recuperados",
                                            summary = "Respuesta exitosa con una lista de trabajadores de campo",
                                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "userId": 5,
                                                "name": "Mateo Lucas",
                                                "lastname": "Hernandez Sanchez",
                                                "phoneNumber": "987654321",
                                                "profilePicture":"https://example.com/profile.jpg",
                                                "winegrowerId": 1,
                                                "status": "ACTIVE"
                                              }
                                            ]
                                            """
                                    )
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
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la lista de trabajadores de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar los trabajadores de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar la lista de trabajadores de campo.",
                                              "details": [
                                                "Error en el servicio de consulta de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<FieldWorkerResource>> getAll() {
        List<FieldWorker> fieldWorkers = queryService.handle(new GetAllFieldWorkersQuery());
        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/fieldworkers/{fieldWorkerId}*/
    @Operation(
            summary = "Get field worker by ID",
            description = "Recupera un trabajador de campo específico utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldWorkerId",
                            description = "El ID del trabajador de campo a recuperar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el trabajador de campo solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FieldWorkerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de trabajador de campo recuperado",
                                            summary = "Respuesta exitosa con los detalles del trabajador de campo",
                                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "userId": 5,
                                                "name": "Mateo Lucas",
                                                "lastname": "Hernandez Sanchez",
                                                "phoneNumber": "987654321",
                                                "profilePicture":"https://example.com/profile.jpg",
                                                "winegrowerId": 1,
                                                "status": "ACTIVE"
                                              }
                                            ]
                                            """
                                    )
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
                            responseCode = "404",
                            description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de trabajador de campo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Trabajador de campo no encontrado.",
                                              "details": [
                                                "No existe un trabajador de campo con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar el trabajador de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el trabajador de campo con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{fieldWorkerId}")
    public ResponseEntity<FieldWorkerResource> getById(@PathVariable Long fieldWorkerId) {
        return queryService.handle(new GetFieldWorkerByIdQuery(fieldWorkerId))
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    /*GET: /api/v1/fieldworkers/user/{userId}*/
    @Operation(
            summary = "Get field worker by User ID",
            description = "Recupera un trabajador de campo específico utilizando su User ID.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "User ID del trabajador de campo",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el trabajador de campo solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FieldWorkerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de trabajador de campo recuperado",
                                            summary = "Respuesta exitosa con los detalles del trabajador de campo",
                                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "userId": 5,
                                                "name": "Mateo Lucas",
                                                "lastname": "Hernandez Sanchez",
                                                "phoneNumber": "987654321",
                                                "profilePicture":"https://example.com/profile.jpg",
                                                "winegrowerId": 1,
                                                "status": "ACTIVE"
                                              }
                                            ]
                                            """
                                    )
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
                            responseCode = "404",
                            description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de trabajador de campo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Trabajador de campo no encontrado.",
                                              "details": [
                                                "No existe un trabajador de campo con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar el trabajador de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el trabajador de campo con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<FieldWorkerResource> getByUserId(@PathVariable Long userId) {
        return queryService.handle(new GetFieldWorkerByUserIdQuery(userId))
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    /*POST: /api/v1/fieldworkers*/
    @Operation(
            summary = "Create field worker",
            description = "Crea un nuevo trabajador de campo con los datos proporcionados. Este endpoint requiere todos los campos obligatorios para registrar correctamente un trabajador de campo en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo trabajador de campo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateFieldWorkerResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Trabajador de campo creado correctamente. Se devuelve el recurso del trabajador de campo creado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FieldWorkerResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de trabajador de campo creado",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "id": 1,
                                      "userId": 3,
                                      "name": "Mateo Lucas",
                                      "lastname": "Hernandez Sanchez",
                                      "phoneNumber": "987654321",
                                      "profilePicture": "https://example.com/profile.jpg",
                                      "winegrowerId": 1,
                                      "status": "ACTIVE"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Entrada inválida. Datos incorrectos o incompletos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de validación",
                                    summary = "Faltan campos obligatorios",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Hay errores de validación en los datos enviados.",
                                      "details": [
                                        "userId: no puede ser nulo",
                                        "name: no puede ser nulo",
                                        "lastname: no puede ser nulo",
                                        "phoneNumber: no puede ser nulo",
                                        "vinegrowerId: no puede ser nulo"
                                      ]
                                    }
                                    """
                            )
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
                    description = "Error interno del servidor. Ocurrió un problema al intentar crear el trabajador de campo.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al crear el trabajador de campo",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo crear el trabajador de campo.",
                                      "details": [
                                        "Error en el servicio de creación de trabajadores de campo.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<FieldWorkerResource> createFieldWorker(@RequestBody @Valid CreateFieldWorkerResource resource) {
        try {
            CreateFieldWorkerCommand command = CreateFieldWorkerCommandFromResourceAssembler.toCommandFromResource(resource);
            FieldWorker fieldWorker = commandService.handle(command)
                    .orElseThrow(() -> new FieldWorkerNotBeCreated(" Field Worker Command Service Error"));
            FieldWorkerResource fieldWorkerResource = FieldWorkerResourceAssembler.toResource(fieldWorker);
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldWorkerResource);
        } catch (Exception e) {
            throw new FieldWorkerNotBeCreated(e.getMessage());
        }
    }




    /*PUT: /api/v1/fieldworkers/{fieldWorkerId}*/
    @Operation(
            summary = "Update field worker",
            description = "Actualiza un trabajador de campo existente utilizando su ID y los datos proporcionados.",
            parameters = {
                    @Parameter(
                            name = "fieldWorkerId",
                            description = "El ID del trabajador de campo a actualizar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del trabajador de campo a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateFieldWorkerResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve el trabajador de campo actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FieldWorkerResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de trabajador de campo actualizado",
                                    summary = "Respuesta exitosa con los detalles del trabajador de campo actualizado",
                                    value = """
                                    {
                                      "id": 1,
                                      "userId": 3,
                                      "name": "Mateo Lucas",
                                      "lastname": "Hernandez Sanchez",
                                      "phoneNumber": "987654321",
                                      "profilePicture": "https://example.com/profile.jpg",
                                      "winegrowerId": 1,
                                      "status": "ACTIVE"
                                    }
                                    """
                            )
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
                    responseCode = "404",
                    description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de trabajador de campo no encontrado",
                                    summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Trabajador de campo no encontrado.",
                                      "details": [
                                        "No existe un trabajador de campo con el ID proporcionado."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar actualizar el trabajador de campo.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el trabajador de campo",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo actualizar el trabajador de campo.",
                                      "details": [
                                        "Error en el servicio de actualización de trabajadores de campo.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PutMapping(value = "/{fieldWorkerId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FieldWorkerResource> update(
            @PathVariable Long fieldWorkerId,
            @RequestPart(required = false) String name,
            @RequestPart(required = false) String lastname,
            @RequestPart(required = false) String phoneNumber,
            @RequestPart(required = false) String winegrowerId,
            @RequestPart(required = false) MultipartFile image
    ) {
        Long winegrowerIdLong = (winegrowerId != null && !winegrowerId.isEmpty()) ? Long.parseLong(winegrowerId) : null;
        var resource = new UpdateFieldWorkerResource(name, lastname, phoneNumber, winegrowerIdLong, image);
        var command = UpdateFieldWorkerCommandFromResourceAssembler.toCommandFromResource(fieldWorkerId, resource);
        var updatedFieldWorker = commandService.update(command);
        return updatedFieldWorker
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /*DELETE: /api/v1/fieldworkers/{fieldWorkerId}/deactivate*/
    @Operation(
            summary = "Deactivate field worker",
            description = "Desactiva un trabajador de campo existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldWorkerId",
                            description = "El ID del trabajador de campo a desactivar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. El trabajador de campo fue desactivado correctamente."
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
                            responseCode = "404",
                            description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de trabajador de campo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Trabajador de campo no encontrado.",
                                              "details": [
                                                "No existe un trabajador de campo con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar desactivar el trabajador de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al desactivar el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo desactivar el trabajador de campo.",
                                              "details": [
                                                "Error en el servicio de desactivación de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{fieldWorkerId}/deactivate")
    public ResponseEntity<Void> deactivateFieldWorker(@PathVariable Long fieldWorkerId) {
        commandService.logicallyDelete(new DeleteFieldWorkerCommand(fieldWorkerId));
        return ResponseEntity.noContent().build();
    }




    /*DELETE: /api/v1/fieldworkers/{fieldWorkerId}*/
    @Operation(
            summary = "Delete field worker",
            description = "Elimina un trabajador de campo existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldWorkerId",
                            description = "El ID del trabajador de campo a eliminar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. El trabajador de campo fue eliminado correctamente."
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
                            responseCode = "404",
                            description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de trabajador de campo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Trabajador de campo no encontrado.",
                                              "details": [
                                                "No existe un trabajador de campo con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar eliminar el trabajador de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al eliminar el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo eliminar el trabajador de campo.",
                                              "details": [
                                                "Error en el servicio de eliminación de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{fieldWorkerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFieldWorker(@PathVariable Long fieldWorkerId) {
        commandService.physicallyDelete(new DeleteFieldWorkerCommand(fieldWorkerId));
    }




    /*PATCH: /api/v1/fieldworkers/{fieldWorkerId}/activate*/
    @Operation(
            summary = "Activate field worker",
            description = "Activa un trabajador de campo existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldWorkerId",
                            description = "El ID del trabajador de campo a activar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el trabajador de campo activado.",
                            content = @Content(
                                    mediaType = "application .json",
                                    schema = @Schema(implementation = FieldWorkerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de trabajador de campo activado",
                                            summary = "Respuesta exitosa con los detalles del trabajador de campo activado",
                                            value = """
                                            {
                                              "id": 1,
                                              "userId": 3,
                                              "name": "Mateo Lucas",
                                              "lastname": "Hernandez Sanchez",
                                              "phoneNumber": "987654321",
                                              "profilePicture": "https://example.com/profile.jpg",
                                              "winegrowerId": 1,
                                              "status": "ACTIVE"
                                            }
                                            """
                                    )
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
                            responseCode = "404",
                            description = "Trabajador de campo no encontrado. No existe un trabajador de campo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de trabajador de campo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Trabajador de campo no encontrado.",
                                              "details": [
                                                "No existe un trabajador de campo con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar activar el trabajador de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al activar el trabajador de campo",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo activar el trabajador de campo.",
                                              "details": [
                                                "Error en el servicio de activación de trabajadores de campo.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @PatchMapping(value = "/{fieldWorkerId}/activate")
    public ResponseEntity<FieldWorkerResource> activateFieldWorker(@PathVariable Long fieldWorkerId) {
        var activatedFieldWorker = commandService.activate(new ActivateFieldWorkerCommand(fieldWorkerId));
        return activatedFieldWorker
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

