package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
