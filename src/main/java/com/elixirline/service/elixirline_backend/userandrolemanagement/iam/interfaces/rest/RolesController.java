package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetAllRolesQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetRoleByNameQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByIdQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.valueobjects.Roles;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.RoleQueryService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.RoleResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UserResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/{roleName}")
    public ResponseEntity<RoleResource> getRoleByName(@PathVariable Roles roleName) {
        var getRoleByNameQuery = new GetRoleByNameQuery(roleName);
        var role = roleQueryService.handle(getRoleByNameQuery);
        return role.map(value -> ResponseEntity.ok(
                RoleResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
}