package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.PatchInputsResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class PatchInputsCommandFromRequestAssembler {
    public static PatchInputsCommand toCommand(
            Long inputId,
            PatchInputsResource resource,
            MultipartFile image
    ) {
        return new PatchInputsCommand(
                inputId,
                Optional.ofNullable(resource.name()),
                Optional.ofNullable(resource.description()),
                Optional.ofNullable(resource.quantity()),
                Optional.ofNullable(resource.winegrowerId()),
                Optional.ofNullable(resource.unit()),
                Optional.ofNullable(image)
        );
    }
}
