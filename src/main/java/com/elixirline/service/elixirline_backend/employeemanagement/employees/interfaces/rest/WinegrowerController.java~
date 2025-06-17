package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.WinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.ActivateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.DeleteWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllWinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetWinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
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
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/winegrowers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Winegrowers", description = "Winegrower Management Endpoints")
public class WinegrowerController {
    private final WinegrowerCommandService commandService;
    private final WinegrowerQueryService queryService;

    public WinegrowerController(WinegrowerCommandService commandService, WinegrowerQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /*GET: /api/v1/winegrowers*/
    @Operation(
            summary = "Get all winegrowers",
            description = "Recupera una lista de todos los vinicultores disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de vinicultores.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WinegrowerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de viticultores recuperados",
                                            summary = "Respuesta exitosa con una lista de vinicultores",
                                            value = """
                                            [
                                              {
                                                "vinegrowerId": 1,
                                                "userId": 3,
                                                "name": "Juan Guillermo",
                                                "lastname": "Pérez Lira",
                                                "country": "Perú",
                                                "phoneNumber": "923456789",
                                                "profilePicture": "https://example.com/profile.jpg",
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
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la lista de vinicultores.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar los vinicultores",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar la lista de vinicultores.",
                                              "details": [
                                                "Error en el servicio de consulta de vinicultores.",
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
    public ResponseEntity<List<WinegrowerResource>> getAll() {
        List<Winegrower> winegrowers = queryService.handle(new GetAllWinegrowersQuery());
        List<WinegrowerResource> resources = winegrowers.stream()
                .map(WinegrowerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/winegrowers/{winegrowerId}/field-workers*/
    @Operation(
            summary = "Get field workers by winegrower ID",
            description = "Recupera una lista de trabajadores de campo asociados a un vinicultor específico.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor para recuperar los trabajadores de campo.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
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
                                                "fieldWorkerId": 1,
                                                "userId": 8,
                                                "name": "Luis Paolo",
                                                "lastname": "Gómez Pérez",
                                                "phoneNumber": "987654321",
                                                "profilePicture": "https://example.com/profile.jpg",
                                                "vinegrowerId": 1,
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar los trabajadores de campo.",
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
    @GetMapping("/{winegrowerId}/field-workers")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByWinegrowerId(@PathVariable Long winegrowerId) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByWinegrowerIdQuery(winegrowerId)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/winegrowers/{winegrowerId}/field-workers/status/{status}*/
    @Operation(
            summary = "Get field workers by winegrower ID and status",
            description = "Recupera una lista de trabajadores de campo asociados a un vinicultor específico y con un estado determinado.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor para recuperar los trabajadores de campo.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    ),
                    @Parameter(
                            name = "status",
                            description = "El estado de los trabajadores de campo a recuperar.",
                            required = true,
                            schema = @Schema(implementation = EmployeeStatus.class)
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de trabajadores de campo con el estado especificado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FieldWorkerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de trabajadores de campo recuperados por estado",
                                            summary = "Respuesta exitosa con una lista de trabajadores de campo",
                                            value = """
                                            [
                                              {
                                                "fieldWorkerId": 1,
                                                "userId": 3,
                                                "name": "Mateo Andres",
                                                "lastname": "Jimenez Gómez",
                                                "phoneNumber": "987654321",
                                                "profilePicture": "https://example.com/profile.jpg",
                                                "vinegrowerId": 1,
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar los trabajadores de campo.",
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
    @GetMapping("/{winegrowerId}/field-workers/status/{status}")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByWinegrowerIdAndStatus(@PathVariable Long winegrowerId, @PathVariable EmployeeStatus status) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery(winegrowerId, status)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/winegrowers/{winegrowerId}*/
    @Operation(
            summary = "Get winegrower by ID",
            description = "Recupera un vinicultor específico utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor a recuperar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el vinicultor solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WinegrowerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de vinicultor recuperado",
                                            summary = "Respuesta exitosa con los detalles del vinicultor",
                                            value = """
                                            {
                                              "vinegrowerId": 1,
                                              "userId": 5,
                                              "name": "Juan Carlos",
                                              "lastname": "Pérez Gómez",
                                              "country": "Perú",
                                              "phoneNumber": "923456789",
                                              "profilePicture": "https://example.com/profile.jpg",
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar el vinicultor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el vinicultor con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de vinicultores.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{winegrowerId}")
    public ResponseEntity<WinegrowerResource> getById(@PathVariable Long winegrowerId) {
        return queryService.handle(new GetWinegrowerByIdQuery(winegrowerId))
                .map(WinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    /*POST: /api/v1/winegrowers*/
    @Operation(
            summary = "Create winegrower",
            description = "Crea un nuevo vinicultor con los datos proporcionados. Este endpoint requiere todos los campos obligatorios para registrar correctamente un viticultor en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo vinicultor",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateWinegrowerResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Vinicultor creado correctamente. Se devuelve el recurso del vinicultor creado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WinegrowerResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de vinicultor creado",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "vinegrowerId": 1,
                                      "userId": 5,
                                      "name": "Juan Carlos",
                                      "lastname": "Pérez Gómez",
                                      "country": "Perú",
                                      "phoneNumber": "923456789",
                                      "profilePicture": "https://example.com/profile.jpg",
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
                                        "country: no puede ser nulo",
                                        "phoneNumber: no puede ser nulo"
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
                    description = "Error interno del servidor. Ocurrió un problema al intentar crear el vinicultor.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al crear el vinicultor",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo crear el vinicultor.",
                                      "details": [
                                        "Error en el servicio de creación de vinicultores.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<WinegrowerResource> createWinegrower(@RequestBody @Valid CreateWinegrowerResource resource) {
        try {
            CreateWinegrowerCommand command = CreateWinegrowerCommandFromResourceAssembler.toCommandFromResource(resource);
            Winegrower winegrower = commandService.handle(command)
                    .orElseThrow(() -> new WinegrowerNotBeCreated(""));
            WinegrowerResource winegrowerResource = WinegrowerResourceAssembler.toResource(winegrower);
            return ResponseEntity.status(HttpStatus.CREATED).body(winegrowerResource);
        } catch (WinegrowerNotBeCreated e) {
            throw new WinegrowerNotBeCreated(e.getMessage());
        }
    }



    /*PUT: /api/v1/winegrowers/{winegrowerId}*/
    @Operation(
            summary = "Update winegrower",
            description = "Actualiza un vinicultor existente utilizando su ID y los datos proporcionados.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor a actualizar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del vinicultor a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateWinegrowerResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve el vinicultor actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WinegrowerResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de vinicultor actualizado",
                                    summary = "Respuesta exitosa con los detalles del vinicultor actualizado",
                                    value = """
                                    {
                                      "vinegrower Id": 1,
                                      "userId": 5,
                                      "name": "Juan Carlos",
                                      "lastname": "Pérez Gomez",
                                      "country": "Perú",
                                      "phoneNumber": "923456789",
                                      "profilePicture": "https://example.com/profile.jpg",
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
                    description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de vinicultor no encontrado",
                                    summary = "Respuesta cuando no se encuentra el vinicultor",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Vinicultor no encontrado.",
                                      "details": [
                                        "No existe un vinicultor con el ID proporcionado."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar actualizar el vinicultor.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el vinicultor",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo actualizar el vinicultor.",
                                      "details": [
                                        "Error en el servicio de actualización de vinicultores.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PutMapping(value = "/{winegrowerId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<WinegrowerResource> update(
            @PathVariable Long winegrowerId,
            @RequestPart(required = false) String name,
            @RequestPart(required = false) String lastname,
            @RequestPart(required = false) String country,
            @RequestPart(required = false) String phoneNumber,
            @RequestPart(required = false) MultipartFile image
    ) {
        var resource = new UpdateWinegrowerResource(name, lastname, country, phoneNumber, image);
        var command = UpdateWinegrowerCommandFromResourceAssembler.toCommandFromResource(winegrowerId, resource);
        var updatedWinegrower = commandService.update(command);
        return updatedWinegrower
                .map(WinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /*
    @PatchMapping(value = "/{vinegrowerId}")
    public ResponseEntity<VinegrowerResource> partialUpdate(@PathVariable Long vinegrowerId, @RequestBody @Valid UpdateVinegrowerResource resource) {
        var command = UpdateVinegrowerCommandFromResourceAssembler
                .toCommandFromResource(vinegrowerId, resource);

        var updatedVinegrower = commandService.updatePartial(command);

        return updatedVinegrower
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    */




    /*DELETE: /api/v1/winegrowers/{winegrowerId}/deactivate*/
    @Operation(
            summary = "Deactivate winegrower",
            description = "Desactiva un vinicultor existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor a desactivar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. El vinicultor fue desactivado correctamente."
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar desactivar el vinicultor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al desactivar el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo desactivar el vinicultor.",
                                              "details": [
                                                "Error en el servicio de desactivación de vinicultores.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{winegrowerId}/deactivate")
    public ResponseEntity<Void> deactivateWinegrower(@PathVariable Long winegrowerId) {
        commandService.logicallyDelete(new DeleteWinegrowerCommand(winegrowerId));
        return ResponseEntity.noContent().build();
    }



    /*DELETE: /api/v1/winegrowers/{winegrowerId}*/
    @Operation(
            summary = "Delete winegrower",
            description = "Elimina un vinicultor existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor a eliminar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. El vinicultor fue eliminado correctamente."
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar eliminar el vinicultor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al eliminar el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo eliminar el vinicultor.",
                                              "details": [
                                                "Error en el servicio de eliminación de vinicultores.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{winegrowerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWinegrower(@PathVariable Long winegrowerId) {
        commandService.physicallyDelete(new DeleteWinegrowerCommand(winegrowerId));
    }



    /*PATCH: /api/v1/winegrowers/{winegrowerId}/activate*/
    @Operation(
            summary = "Activate winegrower",
            description = "Activa un vinicultor existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "winegrowerId",
                            description = "El ID del vinicultor a activar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el vinicultor activado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WinegrowerResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de vinicultor activado",
                                            summary = "Respuesta exitosa con los detalles del vinicultor activado",
                                            value = """
                                            {
                                              "vinegrowerId": 1,
                                              "userId": 5,
                                              "name": "Juan Arturo",
                                              "lastname": "Lopez Pérez",
                                              "country": "Perú",
                                              "phoneNumber": "923456789",
                                              "profilePicture": "https://example.com/profile.jpg",
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
                            description = "Vinicultor no encontrado. No existe un vinicultor con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de vinicultor no encontrado",
                                            summary = "Respuesta cuando no se encuentra el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Vinicultor no encontrado.",
                                              "details": [
                                                "No existe un vinicultor con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar activar el vinicultor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al activar el vinicultor",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo activar el vinicultor.",
                                              "details": [
                                                "Error en el servicio de activación de vinicultores.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @PatchMapping(value = "/{winegrowerId}/activate")
    public ResponseEntity<WinegrowerResource> activateWinegrower(@PathVariable Long winegrowerId) {
        var activatedWinegrower = commandService.activate(new ActivateWinegrowerCommand(winegrowerId));
        return activatedWinegrower
                .map(WinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
