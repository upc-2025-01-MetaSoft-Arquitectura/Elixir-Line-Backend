package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

public record InputsResource(
        Long id,
        String name,
        String description,
        Double quantity,
        Long winegrowerId,
        String unit,
        String image
) {
}
