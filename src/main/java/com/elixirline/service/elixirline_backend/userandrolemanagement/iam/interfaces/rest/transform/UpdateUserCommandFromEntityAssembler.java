package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.UpdateUserCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromEntityAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource userResource) {
        return new UpdateUserCommand(userId, userResource.email(), userResource.password());
    }
}
