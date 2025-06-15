package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetAllRolesQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetRoleByNameQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.valueobjects.Roles;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.RoleQueryService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.RoleResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.ErrorHandler;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

/**
 * Roles Controller
 * This controller is responsible for handling all the requests related to roles
 */
@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role Management Endpoints")
public class RolesController {
    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }




    /**
     * Get all roles
     * @return list of role resources
     */
    @Operation(
            summary = "Get all roles",
            description = "Recupera una lista de todos los roles disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de roles.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de roles recuperados",
                                            summary = "Respuesta exitosa con una lista de roles",
                                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "name": "VINEGROWER"
                                              },
                                              {
                                                "id": 2,
                                                "name": "FIELD_WORKER"
                                              }
                                            ]
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
                                            summary = "Error al recuperar los roles",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar la lista de roles.",
                                              "details": [
                                                "Error en el servicio de consulta de roles.",
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
    public ResponseEntity<List<RoleResource>> getAllRoles(){
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryService.handle(getAllRolesQuery);
        var rolesResources = roles.stream().map(
                RoleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(rolesResources);
    }




    /**
     * this method returns the role with the given role name
     * @param roleName the role name
     * @return the role resource with the given role name
     */
    @Operation(
            summary = "Get role by name",
            description = "Recupera un rol específico utilizando su nombre.",
            parameters = {
                    @Parameter(
                            name = "roleName",
                            description = "El nombre del rol a recuperar.",
                            required = true,
                            schema = @Schema(implementation = Roles.class)
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el rol solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de rol recuperado",
                                            summary = "Respuesta exitosa con los detalles del rol",
                                            value = """
                                            {
                                              "id": 1,
                                              "name": "VINEGROWER"
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rol no encontrado. No existe un rol con el nombre proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error de rol no encontrado",
                                            summary = "Respuesta cuando no se encuentra el rol",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Rol no encontrado.",
                                              "details": [
                                                "No existe un rol con el nombre proporcionado."
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
                                            summary = "Error al recuperar el rol con el nombre proporcionado",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el rol con el nombre proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de roles.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                   )
                            )
                    )
            }
    )
    @GetMapping(value = "/{roleName}")
    public ResponseEntity<RoleResource> getRoleByName(@PathVariable Roles roleName) {
        var getRoleByNameQuery = new GetRoleByNameQuery(roleName);
        var role = roleQueryService.handle(getRoleByNameQuery);
        return role.map(value -> ResponseEntity.ok(
                RoleResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
}