package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;

import javax.annotation.Nullable;

public record PatchInputsResource(
        @Nullable String name,
        @Nullable String description,
        @Nullable Double quantity,
        @Nullable Long winegrowerId,
        @Nullable UnitType unit
) {
}
