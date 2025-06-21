package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

public class CreateInputsCommandFromRequestAssembler {
    public static CreateInputsCommand toCommand(String name, String description,Long quantity, String units, MultipartFile imageFile) {
        UnitType unitType = UnitType.valueOf(units.toUpperCase());
        return new CreateInputsCommand(name, description,quantity, unitType, imageFile);
    }
}
