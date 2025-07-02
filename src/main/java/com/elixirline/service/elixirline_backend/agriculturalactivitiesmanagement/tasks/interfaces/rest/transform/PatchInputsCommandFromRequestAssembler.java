package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

public class PatchInputsCommandFromRequestAssembler {
    public static PatchInputsCommand toCommand(
            Long inputsId,
            String name,
            String description,
            Long quantity,
            Long winegrowerId,
            String unit,
            MultipartFile image
    ) {
        UnitType unitType = unit != null ? UnitType.valueOf(unit.toUpperCase()) : null;
        return new PatchInputsCommand(inputsId, name, description, quantity, winegrowerId, unitType, image);
    }
}
