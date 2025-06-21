package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

public record UpdateInputsCommand(
        Long inputsId,
        String name,
        String description,
        Long quantity,
        UnitType unit,
        MultipartFile image
) {
}
