package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

public record UpdateInputsCommand(
        Long inputsId,
        String name,
        String description,
        Double quantity,
        Long winegrowerId,
        UnitType unit,
        MultipartFile image
) {
}
