package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
