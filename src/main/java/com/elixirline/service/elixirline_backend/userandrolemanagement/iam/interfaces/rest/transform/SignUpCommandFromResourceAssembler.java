package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.SignUpCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.SignUpResource;
import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.email(), resource.password(), roles);
    }
}