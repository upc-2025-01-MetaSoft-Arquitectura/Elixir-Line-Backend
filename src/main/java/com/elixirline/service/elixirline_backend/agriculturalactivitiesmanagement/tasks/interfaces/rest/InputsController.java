package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllInputsQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByNameQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.InputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.CreateInputsCommandFromRequestAssembler;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.InputsResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform.UpdateInputsCommandFromRequestAssembler;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(value = "api/v1/inputs")
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

    @Operation(
            summary = "Create input",
            description = "Crea un nuevo insumo con nombre, descripción, cantidad, unidad y una imagen.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            examples = @ExampleObject(
                                    name = "Ejemplo de formulario",
                                    summary = "Formulario de ejemplo para crear un insumo",
                                    value = """
                name: Fertilizante Orgánico
                description: Mejora el rendimiento del cultivo.
                quantity: 25
                units: KG
                image: (seleccionar archivo .jpg o .png)
                """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Insumo creado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InputsResource.class)
                            )
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputsResource> createInputs(
            @Parameter(description = "Nombre del insumo. Ej: Fertilizante Orgánico", required = true)
            @RequestParam("name") String name,

            @Parameter(description = "Descripción del insumo. Ej: Mejora el rendimiento del cultivo", required = true)
            @RequestParam("description") String description,

            @Parameter(description = "Cantidad del insumo. Ej: 25", required = true)
            @RequestParam("quantity") Long quantity,

            @Parameter(description = "Unidad de medida (KG, LITRO, ML, UNIDAD)", required = true)
            @RequestParam("units") String units,

            @Parameter(description = "Archivo de imagen (.jpg o .png)", required = true)
            @RequestParam("image") MultipartFile imageFile
    ) {
        var command = CreateInputsCommandFromRequestAssembler.toCommand(name, description, quantity, units, imageFile);
        var inputs = inputsCommandService.handle(command);
        var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs.get());
        return new ResponseEntity<>(inputsResource, HttpStatus.CREATED);
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
        if(inputs.isEmpty()) return ResponseEntity.notFound().build();
        var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs.get());
        return ResponseEntity.ok(inputsResource);
    }

    /*PUT: /api/v1/inputs/{inputsId}*/
    @Operation(
            summary = "Update input",
            description = "Actualiza un insumo existente utilizando su ID y los datos proporcionados.",
            parameters = {
                    @Parameter(
                            name = "inputsId",
                            description = "El ID del insumo a actualizar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del insumo a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(type = "object", requiredProperties = {
                                    "name", "description", "quantity", "unit"
                            }),
                            examples = @ExampleObject(
                                    name = "Ejemplo de input actualizado",
                                    summary = "Actualización exitosa de insumo",
                                    value = """
                {
                  "name": "Fertilizante Orgánico",
                  "description": "Mejora el crecimiento de cultivos",
                  "quantity": 50,
                  "unit": "KG"
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
                  "name": "Fertilizante Orgánico",
                  "description": "Mejora el crecimiento de cultivos",
                  "quantity": 50,
                  "unit": "KG",
                  "image": "https://tubucket.blob.core.windows.net/imagenes/fertilizante.jpg"
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
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputsResource> updateInputs(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long quantity,
            @RequestParam String units,
            @RequestParam(required = false) MultipartFile imageFile
    ) {
        var command = UpdateInputsCommandFromRequestAssembler.toCommand(id, name, description, quantity, units, imageFile);
        var updated = inputsCommandService.handle(command);

        if (updated.isEmpty()) return ResponseEntity.notFound().build();

        var resource = InputsResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(resource);
    }

    /*DELETE: /api/v1/inputs/{inputsId}*/
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
    @DeleteMapping("/{inputsId}")
    public ResponseEntity<?> deleteInputs(@PathVariable Long inputsId) {
        var deleteInputsCommand = new DeleteInputsCommand(inputsId);
        inputsCommandService.handle(deleteInputsCommand);
        return ResponseEntity.ok("Deleted Inputs");
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
        if(inputs.isEmpty()) return ResponseEntity.notFound().build();
        var inputsResource = InputsResourceFromEntityAssembler.toResourceFromEntity(inputs.get());
        return ResponseEntity.ok(inputsResource);
    }
}
