package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.DeleteCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetAllCampaignsQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByIdQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByNameQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignsByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignCommandService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignQueryService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CreateCampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.PatchCampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.UpdateCampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.CampaignResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.CreateCampaignCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.PatchCampaignCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.UpdateCampaignCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

import static com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.examples.CreateCampaignExampleValues.EXAMPLE_COMPLETE;
import static com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.examples.CreateCampaignExampleValues.EXAMPLE_MINIMAL;

@RestController
@RequestMapping(value = "/api/v1/campaigns", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Campaigns", description = "Campaigns Management Endpoints")
public class CapaignsController {
    private final CampaignQueryService campaignQueryService;
    private final CampaignCommandService campaignCommandService;

    public CapaignsController(CampaignQueryService campaignQueryService, CampaignCommandService campaignCommandService) {
        this.campaignQueryService = campaignQueryService;
        this.campaignCommandService = campaignCommandService;
    }


    /*GET: /api/v1/campaigns*/
    @Operation(
            summary = "Get all campaigns",
            description = "Recupera una lista de todas las campañas disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de campañas.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de campañas recuperadas",
                                            summary = "Respuesta exitosa con una lista de campañas",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "name": "Campaña 2023",
                                                        "year": "2023",
                                                        "winegrowerId": 5,
                                                        "batches": 10,
                                                        "status": "EN_PROCESO",
                                                        "startDate": "2023-01-01",
                                                        "endDate": "2023-12-31"
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
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la lista de campañas.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar las campañas",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la lista de campañas.",
                                                      "details": [
                                                        "Error en el servicio de consulta de campañas.",
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
    public ResponseEntity<List<CampaignResource>> getAllCampaigns() {
        var getAllCampaignsQuery = new GetAllCampaignsQuery();
        var campaigns = campaignQueryService.handle(getAllCampaignsQuery);
        var campaignResource = campaigns.stream().map(CampaignResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(campaignResource);
    }

    /*POST: /api/v1/campaigns*/
    @PostMapping
    public ResponseEntity<CampaignResource> createCampaign(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Ejemplos de creación de campaña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo completo", value = EXAMPLE_COMPLETE),
                                    @ExampleObject(name = "Ejemplo mínimo", value = EXAMPLE_MINIMAL)
                            }
                    )
            )
            @RequestBody CreateCampaignResource resource
    ) {
        var command = CreateCampaignCommandFromResourceAssembler.toCommandFromResource(resource);
        var campaign = campaignCommandService.handle(command);
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaign.get());
        return new ResponseEntity<>(campaignResource, HttpStatus.CREATED);
    }


    /*GET: /api/v1/campaigns/{campaignId}*/
    @Operation(
            summary = "Get campaign by ID",
            description = "Recupera una campaña específica utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "campaignId",
                            description = "El ID de la campaña a recuperar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve la campaña solicitada.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de campaña recuperada",
                                            summary = "Respuesta exitosa con los detalles de la campaña",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "name": "Campaña 2023",
                                                      "year": "2023",
                                                      "winegrowerId": 5,
                                                      "batches": 10,
                                                      "status": "EN_PROCESO",
                                                      "startDate": "2023-01-01",
                                                      "endDate": "2023-12-31"
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
                            description = "Campaña no encontrada. No existe una campaña con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de campaña no encontrada",
                                            summary = "Respuesta cuando no se encuentra la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Campaña no encontrada.",
                                                      "details": [
                                                        "No existe una campaña con el ID proporcionado."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la campaña.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la campaña con el ID proporcionado.",
                                                      "details": [
                                                        "Error en el servicio de consulta de campañas.",
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignResource> getCampaignById(@PathVariable Long campaignId) {
        var getCampaignByIdQuery = new GetCampaignByIdQuery(campaignId);
        var campaign = campaignQueryService.handle(getCampaignByIdQuery);
        if (campaign.isEmpty()) return ResponseEntity.badRequest().build();
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaign.get());
        return ResponseEntity.ok(campaignResource);
    }


    /* GET: /api/v1/campaigns/winegrower/{winegrowerId} */
    @Operation(
            summary = "Obtener campañas por ID de agricultor",
            description = "Devuelve todas las campañas asociadas a un winegrowerId.",
            parameters = {
                    @Parameter(name = "winegrowerId", description = "ID del agricultor", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de campañas encontradas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignResource.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "name": "Campaña 2023",
                                                        "year": "2023",
                                                        "winegrowerId": 5,
                                                        "batches": 10,
                                                        "status": "EN_PROCESO",
                                                        "startDate": "2023-01-01",
                                                        "endDate": "2023-12-31"
                                                      }
                                                    ]
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/winegrower/{winegrowerId}")
    public ResponseEntity<List<CampaignResource>> getCampaignsByWinegrowerId(@RequestParam Long winegrowerId) {
        var query = new GetCampaignsByWinegrowerIdQuery(winegrowerId);
        var campaigns = campaignQueryService.handle(query);
        var resources = campaigns.stream().map(CampaignResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }


    /*GET: /api/v1/campaigns/search*/
    @Operation(
            summary = "Get campaign by name",
            description = "Recupera una campaña específica utilizando su nombre.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "El nombre de la campaña a recuperar.",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve la campaña solicitada.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CampaignResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de campaña recuperada por nombre",
                                            summary = "Respuesta exitosa con los detalles de la campaña",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "name": "Campaña 2023",
                                                      "year": "2023",
                                                      "winegrowerId": 5,
                                                      "batches": 10,
                                                      "status": "EN_PROCESO",
                                                      "startDate": "2023-01-01",
                                                      "endDate": "2023-12-31"
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
                            description = "Campaña no encontrada. No existe una campaña con el nombre proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de campaña no encontrada",
                                            summary = "Respuesta cuando no se encuentra la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Campaña no encontrada.",
                                                      "details": [
                                                        "No existe una campaña con el nombre proporcionado."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al recuperar la campaña.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo recuperar la campaña con el nombre proporcionado.",
                                                      "details": [
                                                        "Error en el servicio de consulta de campañas.",
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
    public ResponseEntity<CampaignResource> getCampaignByName(@RequestParam String name) {
        var query = new GetCampaignByNameQuery(name);
        var campaign = campaignQueryService.handle(query);
        if (campaign.isEmpty()) return ResponseEntity.notFound().build();
        var resource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaign.get());
        return ResponseEntity.ok(resource);
    }


    /*DELETE: /api/v1/campaigns/{campaignId}*/
    @Operation(
            summary = "Delete campaign",
            description = "Elimina una campaña existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "campaignId",
                            description = "El ID de la campaña a eliminar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Operación exitosa. La campaña fue eliminada correctamente."
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
                            description = "Campaña no encontrada. No existe una campaña con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error de campaña no encontrada",
                                            summary = "Respuesta cuando no se encuentra la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "Campaña no encontrada.",
                                                      "details": [
                                                        "No existe una campaña con el ID proporcionado."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor. Ocurrió un problema al intentar eliminar la campaña.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al eliminar la campaña",
                                            value = """
                                                    {
                                                      "status": "ERROR",
                                                      "message": "No se pudo eliminar la campaña.",
                                                      "details": [
                                                        "Error en el servicio de eliminación de campañas.",
                                                        "Revisar logs del backend para más detalles."
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long campaignId) {
        var deleteCampaignCommand = new DeleteCampaignCommand(campaignId);
        campaignCommandService.handle(deleteCampaignCommand);
        return ResponseEntity.ok("Deleted Campaign");
    }


    /*PUT: /api/v1/campaigns/{campaignId}*/
    @Operation(
            summary = "Update campaign",
            description = "Actualiza una campaña existente utilizando su ID y los datos proporcionados.",
            parameters = {
                    @Parameter(
                            name = "campaignId",
                            description = "El ID de la campaña a actualizar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la campaña a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateCampaignResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve la campaña actualizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CampaignResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de campaña actualizada",
                                    summary = "Respuesta exitosa con los detalles de la campaña actualizada",
                                    value = """
                                            {
                                              "id": 1,
                                              "name": "Campaña 2023 Actualizada",
                                              "year": "2023",
                                              "winegrowerId": 5,
                                              "batches": 10,
                                              "status": "FINALIZADO",
                                              "startDate": "2023-01-01",
                                              "endDate": "2023-12-31"
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
                    description = "Campaña no encontrada. No existe una campaña con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de campaña no encontrada",
                                    summary = "Respuesta cuando no se encuentra la campaña",
                                    value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Campaña no encontrada.",
                                              "details": [
                                                "No existe una campaña con el ID proporcionado."
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar actualizar la campaña.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar la campaña",
                                    value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo actualizar la campaña.",
                                              "details": [
                                                "Error en el servicio de actualización de campañas.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping("/{campaignId}")
    public ResponseEntity<CampaignResource> updateCampaign(@PathVariable Long campaignId, @RequestBody @Valid UpdateCampaignResource updateCampaignResource) {
        var updateCommand = UpdateCampaignCommandFromResourceAssembler.toCommandFromResource(campaignId, updateCampaignResource);
        var updated = campaignCommandService.handle(updateCommand);
        if (updated.isEmpty()) return ResponseEntity.badRequest().build();
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(campaignResource);
    }


    /*PATCH: /api/v1/campaigns/{campaignId}*/
    @PatchMapping("/{campaignId}")
    @Operation(
            summary = "Actualizar parcialmente una campaña",
            description = "Permite actualizar los campos deseados de una campaña existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PatchCampaignResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de PATCH de campaña",
                                    summary = "Actualización parcial",
                                    value = """
                {
                  "name": "Nueva Campaña 2024",
                  "year": "2024",
                  "startDate": "2024-02-01",
                  "endDate": "2024-12-15"
                }
                """
                            )
                    )
            )
    )
    public ResponseEntity<CampaignResource> patchCampaign(@PathVariable Long campaignId,@RequestBody PatchCampaignResource resource) {
        var command = PatchCampaignCommandFromResourceAssembler.toCommandFromResource(campaignId, resource);
        var updated = campaignCommandService.handle(command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();
        var resourceUpdated = CampaignResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(resourceUpdated);
    }
}
