package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands;

public record UpdateUserCommand (Long userId, String email, String password) {
}
