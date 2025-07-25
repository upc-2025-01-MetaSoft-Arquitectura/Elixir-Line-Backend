package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllInputsQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByNameQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.CreateInputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.InputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.PatchInputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.UpdateInputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.CreateInputsCommandFromRequestAssembler;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.InputsResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.PatchInputsCommandFromRequestAssembler;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.UpdateInputsCommandFromRequestAssembler;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(value = "api/v1/inputs", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inputs", description = "Input Management Endpoints")
public class InputsController {
    private final InputsQueryService inputsQueryService;
    private final InputsCommandService inputsCommandService;

    public InputsController(InputsQueryService inputsQueryService, InputsCommandService inputsCommandService) {
        this.inputsQueryService = inputsQueryService;
        this.inputsCommandService = inputsCommandService;
    }




    /*GET: /api/v1/inputs*/
    @Operation(
            summary = "Get all inputs",
            description = "Recupera una lista de todos los insumos disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de insumos.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InputsResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de insumos recuperados",
                                            summary = "Respuesta exitosa con una lista de insumos",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "name": "Fertilizante A",
                                                        "description": "Fertilizante de alta calidad",
                                                        "quantity": 20,
                                                        "unit": "KG",
                                                        "image": "https://elixirblob.blob.core.windows.net/inputs/fertilizanteA.png"
                                                      },
                                                      {
                                                        "id": 2,
                                                        "name": "Insecticida B",
                                                        "description": "Insecticida ecológico",
                                                        "quantity": 10,
                                                        "unit": "LITRO",
                                                        "image": "https://elixirblob.blob.core.windows.net/inputs/insecticidaB.png"
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
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la lista de insumos.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar los insumos",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la lista de insumos.",
                                                      "details": [
                                                        "Error en el servicio de consulta de insumos.",
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
    public ResponseEntity<List<InputsResource>> getAllInputs() {
        var getAllInputsQuery = new GetAllInputsQuery();
        var inputs = inputsQueryService.handle(getAllInputsQuery);
        var inputsResource = inputs.stream().map(InputsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inputsResource);
    }




    /*GET: /api/v1/inputs/{inputsId}*/
    @Operation(
            summary = "Get input by ID",
            description = "Recupera un insumo específico utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "inputsId",
                            description = "El ID del insumo a recuperar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el insumo solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InputsResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de insumo recuperado",
                                            summary = "Respuesta exitosa con los detalles del insumo",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "name": "Fertilizante A",
                                                      "description": "Fertilizante de alta calidad",
                                                      "quantity": 20,
                                                      "unit": "KG",
                                                      "image": "https://elixirblob.blob.core.windows.net/inputs/fertilizanteA.png"
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
                            description = "Insumo no encontrado. No existe un insumo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de insumo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Insumo no encontrado.",
                                                      "details": [
                                                        "No existe un insumo con el ID proporcionado."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar el insumo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar el insumo con el ID proporcionado.",
                                                      "details": [
                                                        "Error en el servicio de consulta de insumos.",
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{inputsId}")
    public ResponseEntity<InputsResource> getInputs(@PathVariable Long inputsId) {
        var getCInputsByIdQuery = new GetInputsByIdQuery(inputsId);
        var inputs = inputsQueryService.handle(getCInputsByIdQuery);
        if (inputs.isEmpty()) return ResponseEntity.notFound().build();
        var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs.get());
        return ResponseEntity.ok(inputsResource);
    }




    /*GET: /api/v1/inputs/search*/
    @Operation(
            summary = "Get input by name",
            description = "Recupera un insumo específico utilizando su nombre.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "El nombre del insumo a recuperar.",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el insumo solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InputsResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de insumo recuperado",
                                            summary = "Respuesta exitosa con los detalles del insumo",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "name": "Fertilizante A",
                                                      "description": "Fertilizante de alta calidad",
                                                      "quantity": 20,
                                                      "unit": "KG",
                                                      "image": "https://storage.azure.net/insumos/fertilizante-a.png"
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
                            description = "Insumo no encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Insumo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Insumo no encontrado.",
                                                      "details": [
                                                        "No existe un insumo con el nombre proporcionado."
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
                                            summary = "Error al recuperar el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar el insumo con el nombre proporcionado.",
                                                      "details": [
                                                        "Error en el servicio de consulta de insumos.",
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<InputsResource> getInputsByName(@RequestParam String name) {
        var query = new GetInputsByNameQuery(name);
        var inputs = inputsQueryService.handle(query);
        if (inputs.isEmpty()) return ResponseEntity.notFound().build();
        var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs.get());
        return ResponseEntity.ok(inputsResource);
    }




    /*GET: /api/v1/inputs/winegrower/{winegrowerId}*/
    @GetMapping("/winegrower/{winegrowerId}")
    @Operation(
            summary = "Obtener insumos por ID de agricultor",
            description = "Devuelve todos los insumos registrados para un determinado agricultor (winegrowerId).",
            parameters = {
                    @Parameter(name = "winegrowerId", description = "ID del agricultor", required = true)
            }
    )
    public ResponseEntity<List<InputsResource>> getInputsByWinegrowerId(@PathVariable Long winegrowerId) {
        var query = new GetInputsByWinegrowerIdQuery(winegrowerId);
        var inputs = inputsQueryService.handle(query);
        var resources = inputs.stream().map(InputsResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }




    /*POST: /api/v1/inputs*/
    @Operation(
            summary = "Create input",
            description = "Crea un nuevo insumo con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo insumo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateInputsResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación de insumo",
                                    summary = "Solicitud de creación de insumo",
                                    value = """
                                    {
                                      "name": "Nuevo Fertilizante",
                                      "description": "Fórmula especial para cultivos orgánicos",
                                      "quantity": 20.0,
                                      "winegrowerId": 3,
                                      "unit": "KG",
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
                    description = "Operación exitosa. Se devuelve el insumo actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InputsResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de insumo actualizado",
                                    summary = "Respuesta exitosa con los detalles del insumo actualizado",
                                    value = """
                                    {
                                      "id": 1,
                                      "name": "Fertilizante Mejorado",
                                      "description": "Fórmula mejorada para mayor rendimiento",
                                      "quantity": 15.5,
                                      "winegrowerId": 3,
                                      "units": "KG",
                                      "image": "https://storage.azure.net/insumos/fertilizante-1.jpg"
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
                    responseCode = "404",
                    description = "Insumo no encontrado. No existe un insumo con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de insumo no encontrado",
                                    summary = "Respuesta cuando no se encuentra el insumo",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Insumo no encontrado.",
                                      "details": [
                                        "No existe un insumo con el ID proporcionado."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar actualizar el insumo.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el insumo",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo actualizar el insumo.",
                                      "details": [
                                        "Error en el servicio de actualización de insumos.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputsResource> createInputs(
            @RequestPart("input") @Valid CreateInputsResource resource,
            @RequestPart ("image") MultipartFile image
    ) {
        try {
            var command = CreateInputsCommandFromRequestAssembler.toCommand(resource, image);
            var inputs = inputsCommandService.handle(command)
                    .orElseThrow(() -> new RuntimeException("Error al crear el insumo"));

            var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs);
            return ResponseEntity.status(HttpStatus.CREATED).body(inputsResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    /*PUT: /api/v1/inputs/{inputId}*/
    @PutMapping(
            value = "/{inputId}",
            consumes = MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update Input",
            description = "Actualiza un insumo existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateInputsResource.class)
                    )
            )
    )
    public ResponseEntity<InputsResource> updateInputs(
            @PathVariable Long inputId,
            @RequestPart(required = false) String name,
            @RequestPart(required = false) String description,
            @RequestPart(required = false) String quantity,
            @RequestPart(required = false) String winegrowerId,
            @RequestPart(required = false) String unit,
            @RequestPart(required = false) MultipartFile image
    ) {
        try {
            Double quantityDouble = (quantity != null && !quantity.isEmpty()) ?
                    Double.parseDouble(quantity) : null;
            Long winegrowerIdLong = (winegrowerId != null && !winegrowerId.isEmpty()) ?
                    Long.parseLong(winegrowerId) : null;

            var resource = new UpdateInputsResource(
                    name,
                    description,
                    quantityDouble,
                    winegrowerIdLong,
                    unit,
                    image
            );

            var command = UpdateInputsCommandFromRequestAssembler.toCommand(inputId, resource);
            var updated = inputsCommandService.handle(command);

            return updated.map(InputsResourceFromEntityAssembler::toResourceFromEntity)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Formato de número inválido: " + e.getMessage()
            );
        }
    }



    /*PATCH: /api/v1/inputs/{inputId}*/
    @PatchMapping(value = "/{inputId}", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Update input partially",
            description = "Actualiza parcialmente un insumo existente"
    )
    public ResponseEntity<InputsResource> patchInputs(
            @PathVariable Long inputId,
            @RequestPart(required = false) String name,
            @RequestPart(required = false) String description,
            @RequestPart(required = false) String quantity,
            @RequestPart(required = false) String winegrowerId,
            @RequestPart(required = false) String unit,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        Double quantityDouble = quantity != null ? Double.parseDouble(quantity) : null;
        Long winegrowerIdLong = winegrowerId != null ? Long.parseLong(winegrowerId) : null;
        UnitType unitType = unit != null ? UnitType.valueOf(unit) : null;

        var resource = new PatchInputsResource(
                name,
                description,
                quantityDouble,
                winegrowerIdLong,
                unitType
        );

        var command = PatchInputsCommandFromRequestAssembler.toCommand(inputId, resource, image);
        var updated = inputsCommandService.handle(command);

        return updated.map(InputsResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /*DELETE: /api/v1/inputs/{inputId}*/
    @Operation(
            summary = "Delete input",
            description = "Elimina un insumo existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "inputsId",
                            description = "El ID del insumo a eliminar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. El insumo fue eliminado correctamente."
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
                            description = "Insumo no encontrado. No existe un insumo con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de insumo no encontrado",
                                            summary = "Respuesta cuando no se encuentra el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Insumo no encontrado.",
                                                      "details": [
                                                        "No existe un insumo con el ID proporcionado."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar eliminar el insumo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al eliminar el insumo",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo eliminar el insumo.",
                                                      "details": [
                                                        "Error en el servicio de eliminación de insumos.",
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{inputId}")
    public ResponseEntity<?> deleteInputs(@PathVariable Long inputId) {
        var deleteInputsCommand = new DeleteInputsCommand(inputId);
        inputsCommandService.handle(deleteInputsCommand);
        return ResponseEntity.ok("Deleted Inputs");
    }
}
