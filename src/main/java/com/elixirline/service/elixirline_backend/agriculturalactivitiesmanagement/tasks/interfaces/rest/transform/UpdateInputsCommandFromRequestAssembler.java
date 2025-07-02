package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

public class UpdateInputsCommandFromRequestAssembler {
    public static UpdateInputsCommand toCommand(Long id, String name, String description, Long quantity, Long winegrowerId, String unit, MultipartFile imageFile) {
        UnitType unitType = UnitType.valueOf(unit.toUpperCase());
        return new UpdateInputsCommand(id, name, description, quantity, winegrowerId,unitType, imageFile);
    }
}
