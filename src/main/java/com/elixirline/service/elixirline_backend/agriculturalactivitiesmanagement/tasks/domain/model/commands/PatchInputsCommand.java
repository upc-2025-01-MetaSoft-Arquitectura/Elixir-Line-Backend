package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public record PatchInputsCommand(
        Long inputsId,
        Optional<String> name,
        Optional<String> description,
        Optional<Double> quantity,
        Optional<Long> winegrowerId,
        Optional<UnitType> unit,
        Optional<MultipartFile> image
) {
    public PatchInputsCommand {
        name = Optional.ofNullable(name).orElse(Optional.empty());
        description = Optional.ofNullable(description).orElse(Optional.empty());
        quantity = Optional.ofNullable(quantity).orElse(Optional.empty());
        winegrowerId = Optional.ofNullable(winegrowerId).orElse(Optional.empty());
        unit = Optional.ofNullable(unit).orElse(Optional.empty());
        image = Optional.ofNullable(image).orElse(Optional.empty());
    }
}
