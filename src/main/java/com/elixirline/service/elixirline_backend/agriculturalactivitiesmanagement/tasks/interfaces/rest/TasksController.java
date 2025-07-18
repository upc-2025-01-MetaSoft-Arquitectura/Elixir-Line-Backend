package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllTasksByWinegrowerIdByFieldWorkerIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTasksByTypeQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTaskByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.*;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/tasks")
@Tag(name = "Task", description = "Task Management Endpoints")
@RequiredArgsConstructor
public class TasksController {
    private final TasksQueryService tasksQueryService;
    private final TasksCommandService tasksCommandService;
    private final EvidenceCommandService evidenceCommandService;




    /* GET: /api/v1/tasks/winegrower/{winegrowerId} */
    @Operation(
            summary = "Obtener tareas por ID del viticultor",
            description = "Devuelve una lista de tareas asociadas a un viticultor específico según su ID.",
            parameters = {
                    @Parameter(name = "winegrowerId", description = "ID del viticultor", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de tareas encontrada.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Tareas por viticultor",
                                            summary = "Lista de tareas",
                                            value = """
                                                [
                                                  {
                                                    "id": 10,
                                                    "title": "Revisión de sensores",
                                                    "description": "Monitoreo de sensores",
                                                    "startDate": "2025-07-01",
                                                    "endDate": "2025-07-03",
                                                    "winegrowerId": "5",
                                                    "batchId": 12,
                                                    "suppliesIds": [301, 302],
                                                    "progressPercentage": 50,
                                                    "status": "IN_PROGRESS",
                                                    "type": "TASK_FIELD"
                                                  },
                                                  {
                                                    "id": 11,
                                                    "title": "Mantenimiento de equipos",
                                                    "description": "Verificación de maquinaria industrial",
                                                    "startDate": "2025-07-05",
                                                    "endDate": "2025-07-07",
                                                    "winegrowerId": "5",
                                                    "batchId": 13,
                                                    "suppliesIds": [],
                                                    "progressPercentage": 100,
                                                    "status": "COMPLETED",
                                                    "type": "TASK_INDUSTRIAL"
                                                  }
                                                ]
                                                """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron tareas para el viticultor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Sin tareas",
                                            summary = "El ID proporcionado no tiene tareas",
                                            value = """
                                                {
                                                  "status": "ERROR",
                                                  "message": "No se encontraron tareas.",
                                                  "details": [
                                                    "El ID de viticultor no tiene tareas asignadas."
                                                  ]
                                                }
                                                """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/winegrower/{winegrowerId}")
    public ResponseEntity<List<TasksResource>> getTasksByWinegrowerId(@PathVariable Long winegrowerId) {
        var tasks = tasksQueryService.findByWinegrowerId(winegrowerId);
        if (tasks.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var resources = tasks.stream().map(TasksResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }




    /* GET: /api/v1/tasks/{taskId} */
    @Operation(
            summary = "Obtener una tarea por ID",
            description = "Recupera los datos de una tarea específica (ya sea de campo o industrial) usando su ID.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID de la tarea que se desea obtener", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve la tarea correspondiente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Tarea encontrada",
                                            summary = "Ejemplo de tarea obtenida exitosamente",
                                            value = """
                                                    {
                                                      "id": 25,
                                                      "title": "Revisión de sensores",
                                                      "description": "Inspeccionar los sensores del sistema de monitoreo agrícola",
                                                      "startDate": "2025-07-20",
                                                      "endDate": "2025-07-21",
                                                      "winegrowerId": "2",
                                                      "fieldWorkerName": "Juan Pérez",
                                                      "batchId": 60,
                                                      "suppliesIds": [601, 602],
                                                      "progressPercentage": 50,
                                                      "status": "IN_PROGRESS",
                                                      "type": "TASK_FIELD"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida. El ID proporcionado no corresponde a ninguna tarea.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Tarea no encontrada",
                                            summary = "ID no válido",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se encontró ninguna tarea con el ID proporcionado.",
                                                      "details": [
                                                        "Verifique que el ID sea correcto y que la tarea exista en el sistema."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al obtener la tarea.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error interno",
                                            summary = "Error inesperado al recuperar la tarea",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Ocurrió un error inesperado al obtener la tarea.",
                                                      "details": [
                                                        "Revisar logs del backend para más información."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{taskId}")
    public ResponseEntity<TasksResource> getTaskById(@PathVariable Long taskId) {
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = tasksQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResource = TasksResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskResource);
    }




    /* GET: /api/v1/tasks/{winegrowerId}/{type} */
    @Operation(
            summary = "Obtener todas las tareas de un tipo, ya sea de campo o industrial",
            description = "Recupera una lista de tareas agrícolas de tipo especificada, ya sea TASK_FIELD o TASK_INDUSTRY.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de tareas de campo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo, listar todas las tareas de campo",
                                            summary = "Ejemplo exitoso de listas todas las tareas de campo",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "title": "Aplicar fertilizante",
                                                        "description": "Aplicar fertilizante al lote 4A",
                                                        "startDate": "2025-07-01",
                                                        "endDate": "2025-07-02",
                                                        "winegrowerId": "1",
                                                        "fieldWorkerName": "Juan Pérez",
                                                        "batchId": 12,
                                                        "suppliesIds": [101, 102],
                                                        "progressPercentage": 50,
                                                        "status": "IN_PROGRESS",
                                                        "type": "TASK_FIELD"
                                                      },
                                                      {
                                                        "id": 2,
                                                        "title": "Riego por goteo",
                                                        "description": "Realizar riego en el campo de vid",
                                                        "startDate": "2025-07-03",
                                                        "endDate": "2025-07-04",
                                                        "winegrowerId": "1",
                                                        "fieldWorkerName": "Juan Pérez",
                                                        "batchId": 14,
                                                        "suppliesIds": [103],
                                                        "progressPercentage": 0,
                                                        "status": "NOT_STARTED",
                                                        "type": "TASK_FIELD"
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
                                            summary = "Token faltante o inválido",
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
                            description = "Error interno del servidor al recuperar las tareas.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error en servidor",
                                            summary = "Fallo al obtener tareas",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la lista de tareas.",
                                                      "details": [
                                                        "Error en el servicio de consulta de tareas.",
                                                        "Revisar los logs del backend para más información."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{winegrowerId}/{type}")
    public ResponseEntity<List<TasksResource>> getTasksPerType(@PathVariable Long winegrowerId, @PathVariable TaskType type) {
        var query = new GetTasksByTypeQuery(winegrowerId, type);
        var tasks = tasksQueryService.handle(query);
        var resources = tasks.stream()
                .map(TasksResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }



    /* GET: /api/v1/tasks/with-evidence/{winegrowerId}/{type} */
    @GetMapping("/with-evidence/{winegrowerId}/{type}")
    @Operation(
            summary = "Obtener tareas, ya sea de campo o de industria con evidencia",
            description = "Devuelve las tareas de un tipo, ya sea TASK_FIELD o TASK_INDUSTRY que tienen al menos una evidencia asociada. Esta lista se utilizará para alimentar la vista de Evidencias."
    )
    public ResponseEntity<List<TasksResource>> getFieldTasksWithEvidence(@PathVariable Long winegrowerId, @PathVariable TaskType type) {
        var tasks = tasksQueryService.findByTypeWithEvidence(winegrowerId, type);
        if (tasks.isEmpty()) return ResponseEntity.noContent().build();
        var resources = tasks.stream()
                .map(TasksResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }


    /* GET: /api/v1/tasks/winegrower/{winegrowerId}/fieldworker/{fieldWorkerId} */
    @Operation(
            summary = "Obtener todas las tareas para un determinado Vinicultor y un determinado trabajador de campo",
            description = "Recupera una lista de tareas agrícolas del vinicultor y trabajador de campo especificados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de tareas de tipo TASK_FIELD y  TASK_INDUSTRY.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo, listar todas las tareas",
                                            summary = "Ejemplo exitoso de listas todas las tareas",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "title": "Aplicar fertilizante",
                                                        "description": "Aplicar fertilizante al lote 4A",
                                                        "startDate": "2025-07-01",
                                                        "endDate": "2025-07-02",
                                                        "winegrowerId": "1",
                                                        "fieldWorkerName": "Juan Pérez",
                                                        "batchId": 12,
                                                        "suppliesIds": [101, 102],
                                                        "progressPercentage": 50,
                                                        "status": "IN_PROGRESS",
                                                        "type": "TASK_FIELD"
                                                      },
                                                      {
                                                        "id": 2,
                                                        "title": "Riego por goteo",
                                                        "description": "Realizar riego en el campo de vid",
                                                        "startDate": "2025-07-03",
                                                        "endDate": "2025-07-04",
                                                        "winegrowerId": "1",
                                                        "fieldWorkerName": "Juan Pérez",
                                                        "batchId": 14,
                                                        "suppliesIds": [103],
                                                        "progressPercentage": 0,
                                                        "status": "NOT_STARTED",
                                                        "type": "TASK_INDUSTRY"
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
                                            summary = "Token faltante o inválido",
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
                            description = "Error interno del servidor al recuperar las tareas.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error en servidor",
                                            summary = "Fallo al obtener tareas",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la lista de tareas.",
                                                      "details": [
                                                        "Error en el servicio de consulta de tareas.",
                                                        "Revisar los logs del backend para más información."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/winegrower/{winegrowerId}/fieldworker/{fieldWorkerId}")
    public ResponseEntity<List<TasksResource>> getTasksByWinegrowerIdAndFieldWorkerId(@PathVariable Long winegrowerId, @PathVariable Long fieldWorkerId) {
        var query = new GetAllTasksByWinegrowerIdByFieldWorkerIdQuery(winegrowerId, fieldWorkerId);
        var tasks = tasksQueryService.findByAllTasksByWinegrowerIdAndFieldWorkerId(query);
        var resources = tasks.stream()
                .map(TasksResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }




    /* POST: /api/v1/tasks */
    @Operation(
            summary = "Crear una tarea",
            description = "Crea una nueva tarea agrícola con la información proporcionada. Al final se debe indicar el tipo de tarea (type), ya sea TASK_FIELD o TASK_INDUSTRY.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear una tarea.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateTaskResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación de tarea",
                                    summary = "Tarea a registrar",
                                    value = """
                                            {
                                              "title": "Siembra de maíz",
                                              "description": "Preparar el terreno y sembrar maíz en el lote 7B",
                                              "startDate": "2025-07-12",
                                              "endDate": "2025-07-14",
                                              "winegrowerId": "12",
                                              "fieldWorkerName": "Juan Pérez",
                                              "fieldWorkerId": 9,
                                              "batchId": 45,
                                              "suppliesIds": [401, 402],
                                              "type": "TASK_FIELD"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Tarea creada exitosamente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Tarea de campo creada",
                                            summary = "Respuesta con la tarea registrada",
                                            value = """
                                                    {
                                                      "id": 15,
                                                      "title": "Siembra de maíz",
                                                      "description": "Preparar el terreno y sembrar maíz en el lote 7B",
                                                      "startDate": "2025-07-12",
                                                      "endDate": "2025-07-14",
                                                      "winegrowerId": "11",
                                                      "fieldWorkerName": "Juan Pérez",
                                                      "batchId": 45,
                                                      "progressPercentage": 0,
                                                      "status": "NOT_STARTED",
                                                      "type": "TASK_FIELD",
                                                      "suppliesIds": [401, 402]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida. Faltan campos requeridos o hay errores en el formato.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Solicitud inválida",
                                            summary = "Faltan campos requeridos",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo crear la tarea.",
                                                      "details": [
                                                        "El campo 'title' es obligatorio.",
                                                        "El campo 'startDate' no puede ser nulo."
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
                                            summary = "Token faltante o inválido",
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
                            description = "Error interno del servidor al crear la tarea.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Fallo en el servidor",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Ocurrió un error inesperado al registrar la tarea.",
                                                      "details": [
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<TasksResource> createTask(@RequestBody CreateTaskResource resource) {
        var command = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var task = tasksCommandService.createTask(command);
        task.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear la tarea."));
        var taskResource = TasksResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }




    /* PUT: /api/v1/tasks/{taskId} */
    @Operation(
            summary = "Actualizar una tarea existente",
            description = "Actualiza los datos de una tarea de campo o industrial a partir de su ID.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID de la tarea a actualizar", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la tarea",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateTasksResource.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Actualizar tarea de campo",
                                            summary = "Modificar datos de una tarea existente de tipo FIELD",
                                            value = """
                            {
                              "title": "Fertilización de invierno",
                              "description": "Aplicar fertilizante en el lote 7",
                              "startDate": "2025-08-01",
                              "endDate": "2025-08-03",
                              "winegrowerId": 4,
                              "fieldWorkerName": "Juan Pérez",
                              "fieldWorkerId": 9,
                              "batchId": 12,
                              "suppliesIds": [101, 102],
                              "type": "TASK_FIELD"
                            }
                            """
                                    ),
                                    @ExampleObject(
                                            name = "Actualizar tarea industrial",
                                            summary = "Modificar datos de una tarea existente de tipo INDUSTRY",
                                            value = """
                            {
                              "title": "Mantenimiento de maquinaria",
                              "description": "Revisión general de equipos de fermentación",
                              "startDate": "2025-08-05",
                              "endDate": "2025-08-07",
                              "winegrowerId": 5,
                              "fieldWorkerName": "Juan Pérez",
                              "batchId": 20,
                              "type": "TASK_INDUSTRY",
                              "suppliesIds": [201, 202]
                            }
                            """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea actualizada exitosamente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la tarea con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida. Datos malformateados o incompletos.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al actualizar la tarea.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    @PutMapping("/{taskId}")
    public ResponseEntity<TasksResource> updateTask(@PathVariable Long taskId, @RequestBody UpdateTasksResource resource) {
        var command = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var update = tasksCommandService.handle(command);
        if (update.isEmpty()) return ResponseEntity.notFound().build();
        var updateResource = TasksResourceFromEntityAssembler.toResourceFromEntity(update.get());
        return ResponseEntity.ok(updateResource);
    }




    /* PATCH: /api/v1/tasks/{taskId} */
    @Operation(
            summary = "Actualizar parcialmente una tarea",
            description = "Permite actualizar solo ciertos campos de una tarea de campo o industrial, sin necesidad de enviar todo el objeto.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID de la tarea a actualizar parcialmente", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Campos que se desean modificar en la tarea",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PatchTaskResource.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Todos los campos actualizados",
                                            summary = "Ejemplo completo de actualización parcial",
                                            value = """
                                            {
                                              "title": "Nueva tarea de campo",
                                              "description": "Aplicación de fertilizante orgánico",
                                              "startDate": "2025-08-01",
                                              "endDate": "2025-08-05",
                                              "winegrowerId": 101,
                                              "fieldWorkerName": "Juan Pérez",
                                              "fieldWorkerId": 9,
                                              "batchId": 202,
                                              "suppliesIds": [301, 302, 303],
                                              "type": "TASK_FIELD"
                                            }
                                            """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea actualizada exitosamente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TasksResource.class),
                                    examples = @ExampleObject(
                                            name = "Respuesta exitosa",
                                            summary = "Datos de la tarea después de la actualización",
                                            value = """
                                            {
                                              "id": 25,
                                              "title": "Nueva tarea de campo",
                                              "description": "Aplicación de fertilizante orgánico",
                                              "startDate": "2025-08-01",
                                              "endDate": "2025-08-05",
                                              "winegrowerId": "5",
                                              "fieldWorkerName": "Juan Pérez",
                                              "batchId": 202,
                                              "suppliesIds": [301, 302, 303],
                                              "progressPercentage": 50,
                                              "status": "IN_PROGRESS",
                                              "type": "TASK_FIELD",
                                              "suppliesIds": [301, 302, 303]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la tarea con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Tarea no encontrada",
                                            summary = "ID de tarea inválido",
                                            value = """
                        {
                          "status": "ERROR",
                          "message": "Tarea no encontrada.",
                          "details": [
                            "No existe una tarea con el ID proporcionado en la base de datos."
                          ]
                        }
                        """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Fallo al actualizar parcialmente",
                                            value = """
                        {
                          "status": "ERROR",
                          "message": "Ocurrió un error inesperado al actualizar la tarea.",
                          "details": [
                            "Revisar los logs del backend para más información."
                          ]
                        }
                        """
                                    )
                            )
                    )
            }
    )
    @PatchMapping("/{taskId}")
    public ResponseEntity<TasksResource> patchTask(@PathVariable Long taskId, @RequestBody PatchTaskResource resource) {
        var command = PatchTaskCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var updated = tasksCommandService.handle(command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();
        var resourceUpdated = TasksResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(resourceUpdated);
    }




    /* DELETE: /api/v1/tasks/{taskId} */
    @Operation(
            summary = "Eliminar una tarea por ID",
            description = "Elimina una tarea existente (de campo o industrial) del sistema según su ID.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID de la tarea que se desea eliminar", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea eliminada exitosamente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Tarea eliminada",
                                            summary = "Confirmación de eliminación",
                                            value = """
                                                    {
                                                      "message": "Task deleted successfully"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la tarea con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Tarea no encontrada",
                                            summary = "ID inexistente",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "La tarea con el ID especificado no fue encontrada.",
                                                      "details": [
                                                        "Verifique que el ID sea correcto y que la tarea exista."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al intentar eliminar la tarea.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Fallo al eliminar la tarea",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Ocurrió un error inesperado al eliminar la tarea.",
                                                      "details": [
                                                        "Revisar los logs del backend para más información."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long taskId) {
        try {
            var command = new DeleteTaskCommand(taskId);
            tasksCommandService.handle(command);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
