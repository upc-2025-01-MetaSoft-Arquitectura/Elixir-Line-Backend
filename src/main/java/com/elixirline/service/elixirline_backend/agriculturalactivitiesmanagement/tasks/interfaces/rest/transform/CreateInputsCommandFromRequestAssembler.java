package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.CreateInputsResource;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.InputsResource;
import org.springframework.web.multipart.MultipartFile;

public class CreateInputsCommandFromRequestAssembler {
    public static CreateInputsCommand toCommand(CreateInputsResource resource, MultipartFile image) {
        return new CreateInputsCommand(
                resource.name(),
                resource.description(),
                resource.quantity(),
                resource.winegrowerId(),
                resource.unit(),
                image
        );
    }
}
