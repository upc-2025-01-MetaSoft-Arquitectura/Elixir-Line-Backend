package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import java.util.List;

public record SignUpCommand(String email, String password, List<Role> roles) {
}
