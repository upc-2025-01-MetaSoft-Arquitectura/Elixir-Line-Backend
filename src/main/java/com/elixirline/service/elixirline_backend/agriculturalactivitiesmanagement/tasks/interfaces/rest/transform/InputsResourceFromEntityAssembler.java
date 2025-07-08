package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.InputsResource;

public class InputsResourceFromEntityAssembler {
    public static InputsResource toResourceFromEntity(Inputs entity) {
        return new InputsResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getQuantity(),
                entity.getWinegrowerId(),
                entity.getUnits() != null ? entity.getUnits().name() : null,
                entity.getImage()
        );
    }
}
