package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands;

public record UpdateUserPasswordCommand(Long userId, String password) {
}
