package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;


import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;

import java.util.List;

public interface EvidenceQueryService {
    List<Evidence> handle(GetEvidenceByTaskIdQuery query);
    List<Evidence> handleByTaskType(TaskType taskType);
}
