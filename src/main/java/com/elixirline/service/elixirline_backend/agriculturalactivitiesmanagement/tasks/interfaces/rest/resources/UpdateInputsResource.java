package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

public record UpdateInputsResource(
        @Nullable  String name,
        @Nullable  String description,
        @Nullable  Double quantity,
        @Nullable  Long winegrowerId,
        @Nullable  String unit,
        @Nullable MultipartFile image
) {
}
