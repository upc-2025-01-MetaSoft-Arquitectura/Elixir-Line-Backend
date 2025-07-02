package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;

import java.util.Optional;

public interface EvidenceCommandService {
    Optional<Evidence> handle(CreateEvidenceCommand command);
}
