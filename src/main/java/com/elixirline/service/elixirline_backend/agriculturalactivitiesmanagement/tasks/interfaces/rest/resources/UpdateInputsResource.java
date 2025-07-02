package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

public record UpdateInputsResource(
        String name,
        String description,
        Long quantity,
        String units
) {
}
