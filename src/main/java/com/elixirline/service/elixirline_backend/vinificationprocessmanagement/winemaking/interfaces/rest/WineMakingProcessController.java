package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest;

import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotBeCreated;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.batch.DeleteBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetAllBatchesQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.queries.batch.GetBatchByIdQuery;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.batch.BatchCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.batch.BatchQueryService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.BatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.CreateBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.PatchBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.batch.UpdateBatchResource;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch.BatchResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch.CreateBatchCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch.PatchBatchCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.batch.UpdateBatchCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/batches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Batches", description = "Batch Management Endpoints")
public class WineMakingProcessController {
    private final BatchCommandService commandService;
    private final BatchQueryService queryService;

    public WineMakingProcessController(BatchCommandService commandService, BatchQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }




    /*GET: /api/v1/batches*/
    @Operation(
            summary = "Get all batches",
            description = "Recupera una lista de todos los lotes en el sistema. Este endpoint devuelve un array de recursos de lotes."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve una lista de lotes.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lotes recuperados",
                                    summary = "Respuesta exitosa con una lista de lotes",
                                    value = """
                                [
                                  {
                                    "batchId": 10,
                                    "campaignId": 5,
                                    "winegrowerId": 1,
                                    "vineyardCode": "B2025-VINEYARD01",
                                    "receptionDate": "2025-06-03",
                                    "harvestCampaign": 2025,
                                    "vineyardOrigin": "Valle de Ica",
                                    "grapeVariety": "MALBEC",
                                    "initialGrapeQuantityKg": 3200,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 0.0,
                                    "status": "NOT_STARTED",
                                    "currentStage": "RECEPTION"
                                  },
                                  {
                                    "batchId": 11,
                                    "campaignId": 6,
                                    "winegrowerId": 2,
                                    "vineyardCode": "B2025-VINEYARD02",
                                    "receptionDate": "2024-01-12",
                                    "harvestCampaign": 2024,
                                    "vineyardOrigin": "Valle del Colca",
                                    "grapeVariety": "Tannat",
                                    "initialGrapeQuantityKg": 2500,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 37.5,
                                    "status": "IN_PROCESS",
                                    "currentStage": "FERMENTATION"
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al recuperar los lotes",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo recuperar la lista de lotes.",
                                  "details": [
                                    "Error en el servicio de consulta de lotes.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<BatchResource>> getAll() {
        List<Batch> batches = queryService.handle(new GetAllBatchesQuery());
        List<BatchResource> resources = batches.stream()
                .map(BatchResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/batches/{batchId}*/
    @Operation(
            summary = "Get batch by ID",
            description = "Recupera un lote por su ID. Proporcione el identificador único del lote para obtener sus detalles."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lote encontrado. Se devuelve el recurso del lote solicitado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lote encontrado",
                                    summary = "Respuesta exitosa con los detalles del lote",
                                    value = """
                                {
                                  "batchId": 10,
                                  "campaignId": 5,
                                  "winegrowerId": 1,
                                  "vineyardCode": "B2025-VINEYARD01",
                                  "receptionDate": "2025-06-03",
                                  "harvestCampaign": 2025,
                                  "vineyardOrigin": "Valle de Ica",
                                  "grapeVariety": "MALBEC",
                                  "initialGrapeQuantityKg": 3200,
                                  "createdBy": "Luis Carlos Prada Naez",
                                  "progress": 0.0,
                                  "status": "NOT_STARTED",
                                  "currentStage": "RECEPTION"
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
                    description = "Lote no encontrado. No existe un lote con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de lote no encontrado",
                                    summary = "Respuesta cuando no se encuentra el lote",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "Lote no encontrado.",
                                  "details": [
                                    "No existe un lote con el ID proporcionado."
                                  ]
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al recuperar el lote",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo recuperar el lote.",
                                  "details": [
                                    "Error en el servicio de consulta de lote.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @GetMapping("/{batchId}")
    public ResponseEntity<BatchResource> getById(
            @Parameter(
                    description = "ID of the batch to retrieve",
                    required = true
            )
            @PathVariable Long batchId
    ) {
        return queryService.handle(new GetBatchByIdQuery(batchId))
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    /*GET: /api/v1/batches/{campaignId}*/
    @Operation(
            summary = "Get all batches by campaign ID",
            description = "Recupera una lista de todos los lotes que pertenecen a una campaña específica en el sistema. Este endpoint devuelve un array de recursos de lotes."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve una lista de lotes pertenecientes a la campaña especificada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lotes recuperados por campaña",
                                    summary = "Respuesta exitosa con una lista de lotes para la campaña especificada",
                                    value = """
                                [
                                  {
                                    "batchId": 10,
                                    "campaignId": 5,
                                    "winegrowerId": 1,
                                    "vineyardCode": "B2025-VINEYARD01",
                                    "receptionDate": "2025-06-03",
                                    "harvestCampaign": 2025,
                                    "vineyardOrigin": "Valle de Ica",
                                    "grapeVariety": "MALBEC",
                                    "initialGrapeQuantityKg": 3200,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 0.0,
                                    "status": "NOT_STARTED",
                                    "currentStage": "RECEPTION"
                                  },
                                  {
                                    "batchId": 11,
                                    "campaignId": 5,
                                    "winegrowerId": 2,
                                    "vineyardCode": "B2025-VINEYARD02",
                                    "receptionDate": "2025-06-04",
                                    "harvestCampaign": 2025,
                                    "vineyardOrigin": "Valle del Colca",
                                    "grapeVariety": "CABERNET SAUVIGNON",
                                    "initialGrapeQuantityKg": 2500,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 37.5,
                                    "status": "IN_PROCESS",
                                    "currentStage": "FERMENTATION"
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al recuperar los lotes por campaña",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo recuperar la lista de lotes para la campaña especificada.",
                                  "details": [
                                    "Error en el servicio de consulta de lotes por campaña.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @GetMapping("/winegrower/{winegrowerId}/campaign/{campaignId}")
    public ResponseEntity<List<BatchResource>> getAllByCampaignId(
            @Parameter(
                    description = "The ID of the campaign.",
                    required = true
            )
            @PathVariable Long winegrowerId,
            @PathVariable Long campaignId
    ) {
        List<Batch> batches = queryService.getAllByCampaignIdByWinegrowerId(winegrowerId, campaignId);
        List<BatchResource> resources = batches.stream()
                .map(BatchResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




    /*GET: /api/v1/batches/winegrower/{winegrowerId}*/
    @Operation(
            summary = "Get all batches by winegrower ID",
            description = "Recupera una lista de todos los lotes que pertenecen a un vinicultor específico en el sistema. Este endpoint devuelve un array de recursos de lotes."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve una lista de lotes pertenecientes al vinicultor especificado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lotes recuperados por vinicultor",
                                    summary = "Respuesta exitosa con una lista de lotes para el vinicultor especificado",
                                    value = """
                                [
                                  {
                                    "batchId": 10,
                                    "campaignId": 5,
                                    "winegrowerId": 1,
                                    "vineyardCode": "B2025-VINEYARD01",
                                    "receptionDate": "2025-06-03",
                                    "harvestCampaign": 2025,
                                    "vineyardOrigin": "Valle de Ica",
                                    "grapeVariety": "MALBEC",
                                    "initialGrapeQuantityKg": 3200,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 0.0,
                                    "status": "NOT_STARTED",
                                    "currentStage": "RECEPTION"
                                  },
                                  {
                                    "batchId": 11,
                                    "campaignId": 6,
                                    "winegrowerId": 1,
                                    "vineyardCode": "B2025-VINEYARD02",
                                    "receptionDate": "2025-06-04",
                                    "harvestCampaign": 2025,
                                    "vineyardOrigin": "Valle de Ica",
                                    "grapeVariety": "CABERNET SAUVIGNON",
                                    "initialGrapeQuantityKg": 2500,
                                    "createdBy": "Luis Carlos Prada Naez",
                                    "progress": 0.0,
                                    "status": "NOT_STARTED",
                                    "currentStage": "RECEPTION"
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al recuperar los lotes por viticultor",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo recuperar la lista de lotes para el vinicultor especificado.",
                                  "details": [
                                    "Error en el servicio de consulta de lotes por vinicultor.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @GetMapping("/winegrower/{winegrowerId}")
    public ResponseEntity<List<BatchResource>> getAllByWinegrowerId(
            @Parameter(
                    description = "The ID of the winegrower.",
                    required = true
            )
            @PathVariable Long winegrowerId
    ) {
        List<Batch> batches = queryService.getAllByWinegrowerId(winegrowerId);
        List<BatchResource> resources = batches.stream()
                .map(BatchResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




    /*POST: /api/v1/batches*/
    @Operation(
            summary = "Create a new batch",
            description = "Crea un nuevo lote de uvas con los datos proporcionados. Este endpoint requiere todos los campos obligatorios para registrar correctamente un lote dentro del sistema de vinificación."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Lote creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lote creado",
                                    summary = "Respuesta exitosa de creación",
                                    value = """
                                    {
                                      "batchId": 10,
                                      "campaignId": 5,
                                      "winegrowerId": 1,
                                      "vineyardCode": "B2025-VINEYARD01",
                                      "vineyardOrigin": "Valle de Ica",
                                      "grapeVariety": "MALBEC",
                                      "harvestCampaign": "2025",
                                      "receptionDate": "2025-06-03",
                                      "initialGrapeQuantityKg": 3200,
                                      "createdBy": "Luis Carlos Prada Naez",
                                      "progress": 0.0,
                                      "status": "NOT_STARTED",
                                      "currentStage": "RECEPTION"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Entrada inválida. Datos incorrectos o incompletos",
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
                                        "campaignId: no puede ser nulo",
                                        "vinegrowerId: no puede ser nulo",
                                        "vineyardCode: no puede ser nulo",
                                        "vineyardOrigin: no puede ser nulo",
                                        "grapeVariety: no puede ser nulo",
                                        "harvestCampaign: no puede ser nulo y no puede ser negativo",
                                        "receptionDate: no puede ser nulo",
                                        "initialGrapeQuantityKg: no puede ser nulo y no puede ser negativo",
                                        "createdBy: no puede ser nulo"
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error en la creación del lote",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo crear el lote.",
                                      "details": [
                                        "Error en el servicio de comando de lote.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<BatchResource> createBatch(@RequestBody @Valid CreateBatchResource resource) {
        CreateBatchCommand command = CreateBatchCommandFromResourceAssembler.toCommandFromResource(resource);
        Batch batch = commandService.handle(command)
                .orElseThrow(() -> new BatchNotBeCreated("Batch Command Service Error"));
        BatchResource batchResource = BatchResourceAssembler.toResource(batch);
        return ResponseEntity.status(HttpStatus.CREATED).body(batchResource);
    }




    /*PUT: /api/v1/batches/{batchId}*/
    @Operation(
            summary = "Update a batch",
            description = "Actualiza un lote existente por su ID. Solo se actualizarán los campos especificados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lote actualizado. Se devuelve el recurso del lote actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lote actualizado",
                                    summary = "Respuesta exitosa con los detalles del lote actualizado",
                                    value = """
                                {
                                  "batchId": 10,
                                  "campaignId": 5,
                                  "winegrowerId": 1,
                                  "vineyardCode": "B2025-VINEYARD01",
                                  "receptionDate": "2025-06-03",
                                  "harvestCampaign": 2025,
                                  "vineyardOrigin": "Valle de Ica",
                                  "grapeVariety": "MALBEC",
                                  "initialGrapeQuantityKg": 3200,
                                  "createdBy": "Luis Carlos Prada Naez",
                                  "progress": 50.0,
                                  "status": "IN_PROCESS",
                                  "currentStage": "FERMENTATION"
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lote no encontrado. No existe un lote con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de lote no encontrado",
                                    summary = "Respuesta cuando no se encuentra el lote a actualizar",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "Lote no encontrado.",
                                  "details": [
                                    "No existe un lote con el ID proporcionado."
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el lote",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo actualizar el lote.",
                                  "details": [
                                    "Error en el servicio de actualización de lote.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @PutMapping("/{batchId}")
    public ResponseEntity<BatchResource> update(@Parameter(description = "ID of the batch to update", required = true)
                                                @PathVariable Long batchId,
                                                @RequestBody @Valid UpdateBatchResource resource) {
        var command = UpdateBatchCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        var updatedBatch = commandService.update(command);
        return updatedBatch
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /*PATCH: /api/v1/batches/{batchId}*/
    @Operation(
            summary = "Patch a batch",
            description = "Actualiza parcialmente un lote existente por su ID. Solo se actualizarán los campos especificados y solo se puede proporcionar un solo campo o varios a actualizar."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lote actualizado. Se devuelve el recurso del lote actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BatchResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de lote actualizado parcialmente",
                                    summary = "Respuesta exitosa con los detalles del lote actualizado",
                                    value = """
                                {
                                  "batchId": 10,
                                  "campaignId": 5,
                                  "winegrowerId": 1,
                                  "vineyardCode": "B2025-VINEYARD01",
                                  "receptionDate": "2025-06-03",
                                  "harvestCampaign": 2025,
                                  "vineyardOrigin": "Valle de Ica",
                                  "grapeVariety": "MALBEC",
                                  "initialGrapeQuantityKg": 3200,
                                  "createdBy": "Luis Carlos Prada Naez",
                                  "progress": 75.0,
                                  "status": "IN_PROCESS",
                                  "currentStage": "FERMENTATION"
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lote no encontrado. No existe un lote con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de lote no encontrado",
                                    summary = "Respuesta cuando no se encuentra el lote a actualizar",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "Lote no encontrado.",
                                  "details": [
                                    "No existe un lote con el ID proporcionado."
                                  ]
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Entrada inválida. Los datos proporcionados son incorrectos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de entrada inválida",
                                    summary = "Respuesta cuando los datos de entrada son incorrectos",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "Entrada inválida.",
                                  "details": [
                                    "El progreso debe estar entre 0 y 100.",
                                    "El estado no es válido."
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
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el lote",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo actualizar el lote.",
                                  "details": [
                                    "Error en el servicio de actualización de lote.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @PatchMapping("/{batchId}")
    public ResponseEntity<BatchResource> patch(@PathVariable Long batchId, @RequestBody @Valid PatchBatchResource resource) {
        var command = PatchBatchCommandFromResourceAssembler.toCommandFromResource(batchId, resource);
        commandService.patch(command);
        return queryService.handle(new GetBatchByIdQuery(batchId))
                .map(BatchResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    /*DELETE: /api/v1/batches/{batchId}*/
    @Operation(
            summary = "Delete a batch",
            description = "Elimina un lote por su ID. Esta operación eliminará permanentemente el lote del sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Lote eliminado. La operación se completó con éxito y no se devuelve contenido."
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
                    description = "Lote no encontrado. No existe un lote con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de lote no encontrado",
                                    summary = "Respuesta cuando no se encuentra el lote a eliminar",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "Lote no encontrado.",
                                  "details": [
                                    "No existe un lote con el ID proporcionado."
                                  ]
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar eliminar el lote.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al eliminar el lote",
                                    value = """
                                {
                                  "status": "ERROR",
                                  "message": "No se pudo eliminar el lote.",
                                  "details": [
                                    "Error en el servicio de eliminación de lote.",
                                    "Revisar logs del backend para más detalles."
                                  ]
                                }
                                """
                            )
                    )
            )
    })
    @DeleteMapping("/{batchId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBatch(@Parameter(description = "ID of the batch to delete", required = true)
                            @PathVariable Long batchId) {
        commandService.delete(new DeleteBatchCommand(batchId));
    }




}
