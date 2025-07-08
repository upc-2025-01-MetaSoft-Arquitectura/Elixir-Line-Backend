package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.UpdateInputsResource;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

public class UpdateInputsCommandFromRequestAssembler {
    public static UpdateInputsCommand toCommand(
            Long inputId,
            UpdateInputsResource resource
    ) {
        return new UpdateInputsCommand(
                inputId,
                resource.name() != null ? resource.name() : null,
                resource.description() != null ? resource.description() : null,
                resource.quantity() != null ? resource.quantity() : null,
                resource.winegrowerId() != null ? resource.winegrowerId() : null,
                resource.unit() != null ? UnitType.valueOf(resource.unit()) : null,
                resource.image() != null ? resource.image() : null
        );
    }
}