package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.UpdateUserPasswordCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UpdateUserPasswordResource;

public class UpdateUserPasswordCommandFromResourceAssembler {
    public static UpdateUserPasswordCommand toCommandFromResource(Long userId, UpdateUserPasswordResource userResource) {
        return new UpdateUserPasswordCommand(userId, userResource.password());
    }
}
