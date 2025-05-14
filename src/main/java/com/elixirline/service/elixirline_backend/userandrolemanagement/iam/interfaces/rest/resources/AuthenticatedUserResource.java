package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String email, String token) {
}
